package com.kenzie.marketing.application.service;

import com.kenzie.marketing.application.controller.model.CreateCustomerRequest;
import com.kenzie.marketing.application.controller.model.CustomerResponse;
import com.kenzie.marketing.application.controller.model.LeaderboardUiEntry;
import com.kenzie.marketing.application.repositories.CustomerRepository;
import com.kenzie.marketing.application.repositories.model.CustomerRecord;

import com.kenzie.marketing.referral.model.CustomerReferrals;
import com.kenzie.marketing.referral.model.LeaderboardEntry;
import com.kenzie.marketing.referral.model.Referral;
import com.kenzie.marketing.referral.model.client.ReferralServiceClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    private CustomerRepository customerRepository;
    private CustomerService customerService;
    private ReferralServiceClient referralServiceClient;

    @BeforeEach
    void setup() {
        customerRepository = mock(CustomerRepository.class);
        referralServiceClient = mock(ReferralServiceClient.class);
        customerService = new CustomerService(customerRepository, referralServiceClient);
    }

    /** ------------------------------------------------------------------------
     *  customerService.findAllCustomers
     *  ------------------------------------------------------------------------ **/

    @Test
    void findAllCustomers_two_customers() {
        // GIVEN
        CustomerRecord record1 = new CustomerRecord();
        record1.setId(randomUUID().toString());
        record1.setName("customername1");
        record1.setDateCreated("recorddate2");

        CustomerRecord record2 = new CustomerRecord();
        record2.setId(randomUUID().toString());
        record2.setName("customername2");
        record2.setDateCreated("recorddate2");

        List<CustomerRecord> recordList = new ArrayList<>();
        recordList.add(record1);
        recordList.add(record2);
        when(customerRepository.findAll()).thenReturn(recordList);

        // WHEN
        List<CustomerResponse> customers = customerService.findAllCustomers();

        // THEN
        Assertions.assertNotNull(customers, "The customer list is returned");
        Assertions.assertEquals(2, customers.size(), "There are two customers");

        for (CustomerResponse customer : customers) {
            if (customer.getId().equals(record1.getId())) {
                Assertions.assertEquals(record1.getId(), customer.getId(), "The customer id matches");
                Assertions.assertEquals(record1.getName(), customer.getName(), "The customer name matches");
                Assertions.assertEquals(record1.getDateCreated(), customer.getDateJoined(), "The customer date matches");
            } else if (customer.getId().equals(record2.getId())) {
                Assertions.assertEquals(record2.getId(), customer.getId(), "The customer id matches");
                Assertions.assertEquals(record2.getName(), customer.getName(), "The customer name matches");
                Assertions.assertEquals(record2.getDateCreated(), customer.getDateJoined(), "The customer date matches");
            } else {
                Assertions.assertTrue(false, "Customer returned that was not in the records!");
            }
        }
    }

    @Test
    void findAllCustomers_two_customers_with_one_as_referrer() {
        // GIVEN
        CustomerRecord record1 = new CustomerRecord();
        record1.setId(randomUUID().toString());
        record1.setName("customername1");
        record1.setDateCreated("recorddate2");

        CustomerRecord record2 = new CustomerRecord();
        record2.setId(randomUUID().toString());
        record2.setName("customername2");
        record2.setDateCreated("recorddate2");
        record2.setReferrerId(record1.getId());

        List<CustomerRecord> recordList = new ArrayList<>();
        recordList.add(record1);
        recordList.add(record2);
        when(customerRepository.findAll()).thenReturn(recordList);
        when(customerRepository.findById(record1.getId())).thenReturn(Optional.of(record1));

        // WHEN
        List<CustomerResponse> customers = customerService.findAllCustomers();

        // THEN
        Assertions.assertNotNull(customers, "The customer list is returned");
        Assertions.assertEquals(2, customers.size(), "There are two customers");

        for (CustomerResponse customer : customers) {
            if (customer.getId().equals(record1.getId())) {
                Assertions.assertEquals(record1.getId(), customer.getId(), "The customer id matches");
                Assertions.assertEquals(record1.getName(), customer.getName(), "The customer name matches");
                Assertions.assertEquals(record1.getDateCreated(), customer.getDateJoined(), "The customer date matches");
            } else if (customer.getId().equals(record2.getId())) {
                Assertions.assertEquals(record2.getId(), customer.getId(), "The customer id matches");
                Assertions.assertEquals(record2.getName(), customer.getName(), "The customer name matches");
                Assertions.assertEquals(record2.getDateCreated(), customer.getDateJoined(), "The customer date matches");
                Assertions.assertEquals(record2.getReferrerId(), customer.getReferrerId(), "The customer referrer ID matches");
            } else {
                Assertions.assertTrue(false, "Customer returned that was not in the records!");
            }
        }
    }
    /** ------------------------------------------------------------------------
     *  customerService.findByCustomerId
     *  ------------------------------------------------------------------------ **/

    @Test
    void getCustomer() {
        // GIVEN
        String customerId = randomUUID().toString();

        CustomerRecord record = new CustomerRecord();
        record.setId(customerId);
        record.setName("customername");
        record.setDateCreated("datecreated");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(record));
        // WHEN
        CustomerResponse customer = customerService.getCustomer(customerId);

        // THEN
        Assertions.assertNotNull(customer, "The customer is returned");
        Assertions.assertEquals(record.getId(), customer.getId(), "The customer id matches");
        Assertions.assertEquals(record.getName(), customer.getName(), "The customer name matches");
        Assertions.assertEquals(record.getDateCreated(), customer.getDateJoined(), "The customer date matches");
    }

    @Test
    void findByCustomerId_invalid_customer() {
        // GIVEN
        String customerId = randomUUID().toString();

        // WHEN
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        CustomerResponse customer = customerService.getCustomer(customerId);

        // THEN
        Assertions.assertNull(customer, "The customer is null when not found");
    }

    /** ------------------------------------------------------------------------
     *  customerService.addNewCustomer
     *  ------------------------------------------------------------------------ **/
    @Test
    void addNewCustomer() {
        // GIVEN
        String customerName = "customerName";

        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setName(customerName);
        request.setReferrerId(Optional.empty());

        ArgumentCaptor<CustomerRecord> customerRecordCaptor = ArgumentCaptor.forClass(CustomerRecord.class);

        // WHEN
        CustomerResponse returnedCustomer = customerService.addNewCustomer(request);

        // THEN
        Assertions.assertNotNull(returnedCustomer);

        verify(customerRepository).save(customerRecordCaptor.capture());

        CustomerRecord record = customerRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The customer record is returned");
        Assertions.assertNotNull(record.getId(), "The customer id exists");
        Assertions.assertEquals(record.getName(), customerName, "The customer name matches");
        Assertions.assertNotNull(record.getDateCreated(), "The customer date exists");
        Assertions.assertNull(record.getReferrerId(), "The referrerId is null");
    }

    @Test
    void addNewCustomer_with_invalid_referrer() {
        // GIVEN
        String customerName = "customerName";

        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setName(customerName);
        request.setReferrerId(Optional.ofNullable(randomUUID().toString()));

        when(customerRepository.existsById(String.valueOf(request.getReferrerId()))).thenReturn(false);

        // THEN
//        Assertions.assertNotNull(returnedCustomer);
        Assertions.assertThrows(ResponseStatusException.class, () -> customerService.addNewCustomer(request));
    }

    /** ------------------------------------------------------------------------
     *  customerService.updateCustomer
     *  ------------------------------------------------------------------------ **/

    @Test
    void updateCustomer() {
        // GIVEN
        String customerId = randomUUID().toString();

        CustomerRecord oldCustomerRecord = new CustomerRecord();
        oldCustomerRecord.setId(customerId);
        oldCustomerRecord.setName("oldcustomername");
        oldCustomerRecord.setDateCreated("olddatecreated");

        String newCustomerName = "newName";

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(oldCustomerRecord));

        ArgumentCaptor<CustomerRecord> customerRecordCaptor = ArgumentCaptor.forClass(CustomerRecord.class);

        // WHEN
        customerService.updateCustomer(customerId, newCustomerName);

        // THEN
        verify(customerRepository).save(customerRecordCaptor.capture());

        CustomerRecord record = customerRecordCaptor.getValue();

        Assertions.assertNotNull(record, "The customer record is returned");
        Assertions.assertEquals(record.getId(), customerId, "The customer id matches");
        Assertions.assertEquals(record.getName(), newCustomerName, "The customer name matches");
        Assertions.assertEquals(record.getDateCreated(), oldCustomerRecord.getDateCreated(), "The customer date has not changed");
    }

    @Test
    void updateCustomer_does_not_exist() {
        // GIVEN
        String customerId = randomUUID().toString();

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // WHEN
        Assertions.assertThrows(ResponseStatusException.class, () -> customerService.updateCustomer(customerId, "newName"));

        // THEN
        try {
            verify(customerRepository, never()).save(Matchers.any());
        } catch(MockitoAssertionError error) {
            throw new MockitoAssertionError("There should not be a call to .save() if the customer is not found in the database. - " + error);
        }

    }

    /** ------------------------------------------------------------------------
     *  customerService.deleteCustomer
     *  ------------------------------------------------------------------------ **/
    @Test
    void deleteCustomer() {
        // GIVEN
        String customerId = randomUUID().toString();

        // WHEN
        customerService.deleteCustomer(customerId);

        // THEN
        verify(customerRepository, times(1)).deleteById(anyString());
    }

    /** ------------------------------------------------------------------------
     *  customerService.calculateBonus
     *  ------------------------------------------------------------------------ **/
    @Test
    void calculateBonus() {
        // GIVEN
        CustomerReferrals referral = new CustomerReferrals();
        referral.setNumFirstLevelReferrals(2);
        referral.setNumSecondLevelReferrals(3);
        referral.setNumThirdLevelReferrals(4);
        Double expected = 33d;

        // WHEN
        when(referralServiceClient.getReferralSummary(anyString())).thenReturn(referral);
        Double actual = customerService.calculateBonus("customer_id");

        // THEN
        Assertions.assertEquals(expected, actual, "The bonuses match");
    }

    /** ------------------------------------------------------------------------
     *  customerService.getReferrals
     *  ------------------------------------------------------------------------ **/
    @Test
    void getReferrals() {
        // GIVEN
        String customerId = randomUUID().toString();
        String referrerId = randomUUID().toString();
        Referral referral = new Referral();
        referral.setCustomerId(customerId);
        referral.setReferralDate("todaysdate");
        referral.setReferrerId(referrerId);
        List<Referral> customerRecords = new ArrayList<>();
        customerRecords.add(referral);

        CustomerRecord record = new CustomerRecord();
        record.setId(customerId);
        record.setDateCreated("todaysdate");
        record.setReferrerId(referrerId);
        record.setName("referrername");

        when(customerRepository.findById(anyString())).thenReturn(Optional.of(record));
        when(customerRepository.existsById(referrerId)).thenReturn(true);
        when(referralServiceClient.getDirectReferrals(referrerId)).thenReturn(customerRecords);

        // WHEN
        List<CustomerResponse> actual = customerService.getReferrals(referrerId);

        // THEN
        Assertions.assertEquals(actual.get(0).getId(), referral.getCustomerId(), "The customer id matches");
        Assertions.assertEquals(actual.get(0).getDateJoined(), referral.getReferralDate(), "The customer date matches");
        Assertions.assertEquals(actual.get(0).getReferrerId(),referral.getReferrerId(), "The customer referrer ID matches");
    }
    @Test
    void getReferrals_customer_does_not_exist() {
        // GIVEN
        when(customerRepository.existsById(anyString())).thenReturn(false);

        // THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> customerService.getReferrals(anyString()));
    }

    /** ------------------------------------------------------------------------
     *  customerService.getLeaderboard
     *  ------------------------------------------------------------------------ **/
    @Test
    void getLeaderboard() {
        // GIVEN
        String customerId = randomUUID().toString();
        String referrerId = randomUUID().toString();
        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setId(customerId);
        customerRecord.setName("testcustomer");
        customerRecord.setDateCreated("todaysdate");
        customerRecord.setReferrerId(referrerId);
        List<CustomerRecord> customerRecords = new ArrayList<>();
        customerRecords.add(customerRecord);
        List<LeaderboardEntry> leaderboardEntries = new ArrayList<>();
        leaderboardEntries.add(new LeaderboardEntry(1, customerId));


        when(referralServiceClient.getLeaderboard()).thenReturn(leaderboardEntries);
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customerRecord));

        // WHEN
        List<LeaderboardUiEntry> results = customerService.getLeaderboard();

        // THEN
        Assertions.assertEquals(results.get(0).getCustomerId(), customerRecord.getId(), "The customer id matches");
        Assertions.assertEquals(results.get(0).getCustomerName(), customerRecord.getName(), "The customer name matches");
        Assertions.assertEquals(results.get(0).getNumReferrals(),1, "The customer number of referral matches");
    }

}
