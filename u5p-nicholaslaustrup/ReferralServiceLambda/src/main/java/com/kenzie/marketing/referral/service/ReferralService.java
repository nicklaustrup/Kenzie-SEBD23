package com.kenzie.marketing.referral.service;

import com.kenzie.marketing.referral.model.CustomerReferrals;
import com.kenzie.marketing.referral.model.LeaderboardEntry;
import com.kenzie.marketing.referral.model.Referral;
import com.kenzie.marketing.referral.model.ReferralRequest;
import com.kenzie.marketing.referral.model.ReferralResponse;
import com.kenzie.marketing.referral.service.converter.ReferralConverter;
import com.kenzie.marketing.referral.service.dao.ReferralDao;
import com.kenzie.marketing.referral.service.exceptions.InvalidDataException;
import com.kenzie.marketing.referral.service.model.ReferralRecord;
import com.kenzie.marketing.referral.service.task.ReferralTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.*;
import java.util.List;
import java.util.stream.Collectors;

public class ReferralService {

    private ReferralDao referralDao;
    private ExecutorService executor;
    static final Logger log = LogManager.getLogger();
    @Inject
    public ReferralService(ReferralDao referralDao) {
        this.referralDao = referralDao;
        this.executor = Executors.newCachedThreadPool();
    }

    // Necessary for testing, do not delete
    public ReferralService(ReferralDao referralDao, ExecutorService executor) {
        this.referralDao = referralDao;
        this.executor = executor;
    }

    public List<LeaderboardEntry> getReferralLeaderboard() {
        List<ReferralRecord> nodes = this.referralDao.findUsersWithoutReferrerId();
        List<Future<LeaderboardEntry>> threadFutures = new ArrayList<>();
        for(ReferralRecord node : nodes) {
            ReferralTask task = new ReferralTask(referralDao, node);
            threadFutures.add(executor.submit(task));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException("Executor was interrupted " + e);
        }
        return condenseLists(threadFutures);
    }

    private List<LeaderboardEntry> condenseLists(List<Future<LeaderboardEntry>> threadFutures) {
        // Condensing Results
        // Extract each return value from threadFutures
        List<LeaderboardEntry> condensedList = new ArrayList<>();

        for (Future<LeaderboardEntry> entry:threadFutures) {
            try{
                // Add them to a new list
                condensedList.add(entry.get());
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e + ". condenseList method failed");
            }
        }
        condensedList.sort(Comparator.comparing(LeaderboardEntry::getNumReferrals).reversed());

        List<LeaderboardEntry> result = new ArrayList<>();
        // Find the top 5 of your new list
        for (int i = 0; i < condensedList.size(); i++){
            if (i > 4){
                break;
            }
            result.add(condensedList.get(i));
        }
        return result;
    }

    public CustomerReferrals getCustomerReferralSummary(String customerId) {
        CustomerReferrals referrals = new CustomerReferrals();

        List<ReferralRecord> referralRecordsFirst = referralDao.findByReferrerId(customerId);
        referrals.setNumFirstLevelReferrals(referralRecordsFirst.size());

        List<ReferralRecord> referralRecordsSecond = new ArrayList<>();
        for (ReferralRecord referralRecord:referralRecordsFirst) {
            referralRecordsSecond.addAll(referralDao.findByReferrerId(referralRecord.getCustomerId()));
        }
        referrals.setNumSecondLevelReferrals(referralRecordsSecond.size());


        List<ReferralRecord> referralRecordsThird = new ArrayList<>();
        for (ReferralRecord referrerRecord3:referralRecordsSecond) {
            referralRecordsThird.addAll(referralDao.findByReferrerId(referrerRecord3.getCustomerId()));
        }
        referrals.setNumThirdLevelReferrals(referralRecordsThird.size());

        return referrals;
    }


    public List<Referral> getDirectReferrals(String customerId) {
        List<ReferralRecord> records = referralDao.findByReferrerId(customerId);


        return records.stream()
                .map(ReferralConverter::fromRecordToReferral)
                .collect(Collectors.toList());
    }


    public ReferralResponse addReferral(ReferralRequest referral) {
        if (referral == null || referral.getCustomerId() == null || referral.getCustomerId().length() == 0) {
            throw new InvalidDataException("Request must contain a valid Customer ID");
        }
        ReferralRecord record = ReferralConverter.fromRequestToRecord(referral);
        referralDao.addReferral(record);
        return ReferralConverter.fromRecordToResponse(record);
    }

    public boolean deleteReferrals(List<String> customerIds){
        boolean allDeleted = true;

        if(customerIds == null){
            throw new InvalidDataException("Request must contain a valid list of Customer ID");
        }

        for(String customerId : customerIds){
            if(customerId == null || customerId.length() == 0){
                throw new InvalidDataException("Customer ID cannot be null or empty to delete");
            }

            ReferralRecord record = new ReferralRecord();
            record.setCustomerId(customerId);

            boolean deleted = referralDao.deleteReferral(record);

            if(!deleted){
                allDeleted = false;
            }
        }
        return allDeleted;
    }

}
