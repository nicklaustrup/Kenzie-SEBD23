package com.kenzie.marketing.application.service;

import com.kenzie.marketing.application.controller.model.CreateCustomerRequest;
import com.kenzie.marketing.application.controller.model.CustomerResponse;
import com.kenzie.marketing.application.controller.model.LeaderboardUiEntry;
import com.kenzie.marketing.application.repositories.CustomerRepository;
import com.kenzie.marketing.application.repositories.model.CustomerRecord;
import com.kenzie.marketing.referral.model.CustomerReferrals;
import com.kenzie.marketing.referral.model.LeaderboardEntry;
import com.kenzie.marketing.referral.model.Referral;
import com.kenzie.marketing.referral.model.ReferralRequest;
import com.kenzie.marketing.referral.model.client.ReferralServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.UUID.randomUUID;

@Service
public class CustomerService {
    private static final Double REFERRAL_BONUS_FIRST_LEVEL = 10.0;
    private static final Double REFERRAL_BONUS_SECOND_LEVEL = 3.0;
    private static final Double REFERRAL_BONUS_THIRD_LEVEL = 1.0;

    private CustomerRepository customerRepository;
    private ReferralServiceClient referralServiceClient;

    public CustomerService(CustomerRepository customerRepository, ReferralServiceClient referralServiceClient) {
        this.customerRepository = customerRepository;
        this.referralServiceClient = referralServiceClient;
    }

    /**
     * findAllCustomers
     * @return A list of Customers
     */
    public List<CustomerResponse> findAllCustomers() {
        List<CustomerRecord> records = StreamSupport.stream(customerRepository.findAll().spliterator(), true).collect(Collectors.toList());

        return records.stream()
                .map(r -> toCustomerResponse(r))
                .peek(response -> response.setReferrerName(getReferrerName(response.getReferrerId()).toString()))
                .collect(Collectors.toList());
    }

    /**
     * findByCustomerId
     * @param customerId
     * @return The Customer with the given customerId
     */
    public CustomerResponse getCustomer(String customerId) {
        Optional<CustomerRecord> record = customerRepository.findById(customerId);

        return toCustomerResponse(record.orElse(null));
    }

    /**
     * addNewCustomer
     *
     * This creates a new customer.  If the referrerId is included, the referrerId must be valid and have a
     * corresponding customer in the DB.  This posts the referrals to the referral service
     * @param createCustomerRequest
     * @return A CustomerResponse describing the customer
     */
    public CustomerResponse addNewCustomer(CreateCustomerRequest createCustomerRequest) {
        String referrerId = createCustomerRequest.getReferrerId().orElse(null);
         if (referrerId != null && !isValidReferral(referrerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Referrer ID");
         }
        CustomerRecord customerRecord = toCustomerRecord(createCustomerRequest, referrerId);
        ReferralRequest referralRequest = new ReferralRequest(customerRecord.getId(), referrerId);
        referralServiceClient.addReferral(referralRequest);

        customerRepository.save(customerRecord);
        return toCustomerResponse(customerRecord);
    }

    /**
     * updateCustomer - This updates the customer name for the given customer id
     * @param customerId - The Id of the customer to update
     * @param customerName - The new name for the customer
     */
    public CustomerResponse updateCustomer(String customerId, String customerName) {
        Optional<CustomerRecord> customerExists = customerRepository.findById(customerId);
        if (customerExists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer Not Found");
        }
        CustomerRecord customerRecord = customerExists.get();
        customerRecord.setName(customerName);
        customerRepository.save(customerRecord);


        return customerExists.stream()
                .map(this::toCustomerResponse)
                .peek(response -> response.setReferrerName(getReferrerName(response.getReferrerId())))
                .collect(Collectors.toList()).get(0);
    }

    /**
     * deleteCustomer - This deletes the customer record for the given customer id
     * @param customerId
     */
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    /**
     * calculateBonus - This calculates the referral bonus for the given customer according to the referral bonus
     * constants.
     * @param customerId
     * @return
     */
    public Double calculateBonus(String customerId) {
        CustomerReferrals referrals = referralServiceClient.getReferralSummary(customerId);

        return REFERRAL_BONUS_FIRST_LEVEL * referrals.getNumFirstLevelReferrals() +
                REFERRAL_BONUS_SECOND_LEVEL * referrals.getNumSecondLevelReferrals() +
                REFERRAL_BONUS_THIRD_LEVEL * referrals.getNumThirdLevelReferrals();
    }

    /**
     * getReferrals - This returns a list of referral entries for every customer directly referred by the given
     * customerId.
     * @param customerId
     * @return
     */
    public List<CustomerResponse> getReferrals(String customerId) {

    //CREDIT: Devon Dillon
        if (!customerRepository.existsById(customerId)){
            throw new IllegalArgumentException("Customer does not exist");
        }
        return Optional.of(referralServiceClient.getDirectReferrals(customerId))
                .orElse(Collections.emptyList())
                .stream()
                .map(this::fromReferralToResponse)
                .collect(Collectors.toList());
    }

    /**
     * getLeaderboard - This calls the referral service to retrieve the current top 5 leaderboard of the most referrals
     * @return
     */
    public List<LeaderboardUiEntry> getLeaderboard() {

        List<LeaderboardUiEntry> leaderboardUiEntries = new ArrayList<>();
        List<LeaderboardEntry> leaderboardEntries = referralServiceClient.getLeaderboard();
        for (LeaderboardEntry entry : leaderboardEntries) {
            Optional<CustomerRecord> record = customerRepository.findById(entry.getCustomerId());
            record.ifPresent(customerRecord -> leaderboardUiEntries.add(new LeaderboardUiEntry(entry.getCustomerId(), customerRecord.getName(), entry.getNumReferrals())));
        }
        return leaderboardUiEntries;
    }

    /* -----------------------------------------------------------------------------------------------------------
        Private Methods
       ----------------------------------------------------------------------------------------------------------- */

    private CustomerResponse toCustomerResponse(CustomerRecord record) {
        if (record == null) {
            return null;
        }
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(record.getId());
        customerResponse.setName(record.getName());
        customerResponse.setReferrerId(record.getReferrerId());
        customerResponse.setDateJoined(record.getDateCreated());

        //credit: Eric from discord
        if (record.getReferrerId() == null || record.getReferrerId().isEmpty()) {
            customerResponse.setReferrerName("");
        } else if (isValidReferral(record.getReferrerId())){
            CustomerRecord customerRecord = customerRepository.findById(record.getReferrerId()).get();
            customerResponse.setReferrerName(customerRecord.getName());
                }

        return customerResponse;
    }

    private CustomerRecord toCustomerRecord(CreateCustomerRequest createCustomerRequest, String referrerId) {
        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setId(randomUUID().toString());
        customerRecord.setName(createCustomerRequest.getName());
        customerRecord.setDateCreated(LocalDateTime.now().toString());
        customerRecord.setReferrerId(referrerId);
        return customerRecord;
    }

    private boolean isValidReferral(String referrerId) {
        return this.customerRepository.existsById(referrerId);
    }

    private String getReferrerName(String id) {
        if (id == null || id.isEmpty()) {
            return "";
        }
        Optional<CustomerRecord> customerExists = customerRepository.findById(id);
        if (customerExists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer Not Found");
        }
        return customerExists.get().getName();
    }

    private CustomerResponse fromReferralToResponse(Referral referral){
        CustomerResponse response = getCustomer(referral.getCustomerId());
        response.setReferrerName(getReferrerName(referral.getReferrerId()));
        response.setReferrerId(referral.getReferrerId());
        return response;
    }
}
