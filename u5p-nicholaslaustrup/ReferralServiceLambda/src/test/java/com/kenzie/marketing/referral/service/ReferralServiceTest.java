package com.kenzie.marketing.referral.service;


import com.kenzie.marketing.referral.model.*;
import com.kenzie.marketing.referral.service.converter.ZonedDateTimeConverter;
import com.kenzie.marketing.referral.service.dao.ReferralDao;
import com.kenzie.marketing.referral.service.exceptions.InvalidDataException;
import com.kenzie.marketing.referral.service.model.ReferralRecord;
import com.kenzie.marketing.referral.service.task.ReferralTask;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReferralServiceTest {

    /** ------------------------------------------------------------------------
     *  expenseService.getExpenseById
     *  ------------------------------------------------------------------------ **/

    private ReferralDao referralDao;
    private ReferralService referralService;

    @BeforeAll
    void setup() {
        this.referralDao = mock(ReferralDao.class);
        this.referralService = new ReferralService(referralDao);
    }

    @Test
    void addReferralTest() {
        ArgumentCaptor<ReferralRecord> referralCaptor = ArgumentCaptor.forClass(ReferralRecord.class);

        // GIVEN
        String customerId = "fakecustomerid";
        String referrerId = "fakereferralid";
        ReferralRequest request = new ReferralRequest();
        request.setCustomerId(customerId);
        request.setReferrerId(referrerId);

        // WHEN
        ReferralResponse response = this.referralService.addReferral(request);

        // THEN
        verify(referralDao, times(1)).addReferral(referralCaptor.capture());
        ReferralRecord record = referralCaptor.getValue();

        assertNotNull(record, "The record is valid");
        assertEquals(customerId, record.getCustomerId(), "The record customerId should match");
        assertEquals(referrerId, record.getReferrerId(), "The record referrerId should match");
        assertNotNull(record.getDateReferred(), "The record referral date exists");

        assertNotNull(response, "A response is returned");
        assertEquals(customerId, response.getCustomerId(), "The response customerId should match");
        assertEquals(referrerId, response.getReferrerId(), "The response referrerId should match");
        assertNotNull(response.getReferralDate(), "The response referral date exists");
    }

    @Test
    void addReferralTest_no_customer_id() {
        // GIVEN
        String customerId = "";
        String referrerId = "";
        ReferralRequest request = new ReferralRequest();
        request.setCustomerId(customerId);
        request.setReferrerId(referrerId);

        // WHEN / THEN
        assertThrows(InvalidDataException.class, ()->this.referralService.addReferral(request));
    }

    @Test
    void getDirectReferralsTest() {
        // GIVEN
        String customerId = "fakecustomerid";
        List<ReferralRecord> recordList = new ArrayList<>();

        ReferralRecord record1 = new ReferralRecord();
        record1.setCustomerId("customer1");
        record1.setReferrerId(customerId);
        record1.setDateReferred(ZonedDateTime.now());
        recordList.add(record1);

        ReferralRecord record2 = new ReferralRecord();
        record2.setCustomerId("customer2");
        record2.setReferrerId(customerId);
        record2.setDateReferred(ZonedDateTime.now());
        recordList.add(record2);

        when(referralDao.findByReferrerId(customerId)).thenReturn(recordList);

        // WHEN
        List<Referral> referrals = this.referralService.getDirectReferrals(customerId);

        // THEN
        verify(referralDao, times(1)).findByReferrerId(customerId);

        assertNotNull(referrals, "The returned referral list is valid");
        assertEquals(2, referrals.size(), "The referral list has 2 items");
        for (Referral referral : referrals) {
            if (record1.getCustomerId().equals(referral.getCustomerId())) {
                assertEquals(record1.getReferrerId(), customerId);
                assertEquals(new ZonedDateTimeConverter().convert(record1.getDateReferred()), referral.getReferralDate());
            } else if (record2.getCustomerId().equals(referral.getCustomerId())) {
                assertEquals(record2.getReferrerId(), customerId);
                assertEquals(new ZonedDateTimeConverter().convert(record2.getDateReferred()), referral.getReferralDate());
            } else {
                fail("A Referral was returned that does not match record 1 or 2.");
            }
        }
    }

    // Write additional tests here
    @Test
    void deleteReferrals(){
        List<String> customerIds = new ArrayList<>();
        customerIds.add("fake123");
        customerIds.add("fake234");
        customerIds.add("fake345");

        when(referralDao.deleteReferral(any())).thenReturn(true);

        boolean actual = referralService.deleteReferrals(customerIds);


        verify(referralDao,times(3)).deleteReferral(any());
        assertTrue(actual, "delete referrals should be true");
    }

    @Test
    void deleteReferrals_nullIds_throwsException(){
        List<String> customerIds = new ArrayList<>();
        customerIds.add(null);

        assertThrows(InvalidDataException.class, () -> referralService.deleteReferrals(customerIds));
    }

    @Test
    void deleteReferrals_emptyIds_throwsException(){
        List<String> customerIds = new ArrayList<>();
        customerIds.add("");

        assertThrows(InvalidDataException.class, () -> referralService.deleteReferrals(customerIds));
    }


    @Test
    void getCustomerReferralSummary(){

        List<ReferralRecord> layer1 = new ArrayList<>();
        layer1.add(new ReferralRecord());
        layer1.add(new ReferralRecord());
        layer1.add(new ReferralRecord());
        List<ReferralRecord> layer2 = new ArrayList<>();
        layer2.add(new ReferralRecord());
        layer2.add(new ReferralRecord());
        layer2.add(new ReferralRecord());
        List<ReferralRecord> layer3 = new ArrayList<>();
        layer3.add(new ReferralRecord());
        layer3.add(new ReferralRecord());
        layer3.add(new ReferralRecord());


        CustomerReferrals expected = new CustomerReferrals();
        expected.setNumFirstLevelReferrals(3);
        expected.setNumSecondLevelReferrals(9);
        expected.setNumThirdLevelReferrals(27);


        when(referralDao.findByReferrerId(any())).thenReturn(layer1);
        when(referralDao.findByReferrerId(any())).thenReturn(layer2);
        when(referralDao.findByReferrerId(any())).thenReturn(layer3);

        CustomerReferrals actual = referralService.getCustomerReferralSummary("fakecustomerid1");

        assertEquals(expected.getNumFirstLevelReferrals(), actual.getNumFirstLevelReferrals(), "Number of first level customer referrals should be 3");
        assertEquals(expected.getNumSecondLevelReferrals(), actual.getNumSecondLevelReferrals(), "Number of second level customer referrals should be 9");
        assertEquals(expected.getNumThirdLevelReferrals(), actual.getNumThirdLevelReferrals(), "Number of third level customer referrals should be 27");

    }
    @Test
    void getLeaderboard(){
        // GIVEN
        List<ReferralRecord> usersWithoutIds = createUserListWithoutId();
        List<ReferralRecord> recordList1 = createReferralRecords("fakecustomerid1");
        List<ReferralRecord> recordList2 = createReferralRecords("fakecustomerid2");
        List<ReferralRecord> recordList3 = createReferralRecords("fakecustomerid3");
        List<ReferralRecord> recordList4 = createReferralRecords("fakecustomerid4");
        List<LeaderboardEntry> expectedResult = new ArrayList<>();
        expectedResult.add(new LeaderboardEntry(3, "fakecustomerid1"));
        expectedResult.add(new LeaderboardEntry(2, "fakecustomerid2"));
        expectedResult.add(new LeaderboardEntry(1, "fakecustomerid3"));
        expectedResult.add(new LeaderboardEntry(0, "fakecustomerid4"));



        when(referralDao.findUsersWithoutReferrerId()).thenReturn(usersWithoutIds);
        when(referralDao.findByReferrerId("fakecustomerid1")).thenReturn(recordList1);
        when(referralDao.findByReferrerId("fakecustomerid2")).thenReturn(recordList2);
        when(referralDao.findByReferrerId("fakecustomerid3")).thenReturn(recordList3);
        when(referralDao.findByReferrerId("fakecustomerid4")).thenReturn(recordList4);

        // WHEN
        List<LeaderboardEntry> referrals = referralService.getReferralLeaderboard();

        // THEN
        verify(referralDao, times(1)).findUsersWithoutReferrerId();

        assertNotNull(referrals, "The returned referral list is valid");
        assertEquals(4, referrals.size(), "The referral list should have 4 items");
        for (int i = 0; i < referrals.size(); i++) {
            assertEquals(expectedResult.get(i).getCustomerId(), referrals.get(i).getCustomerId(), "Referrals should be in correct order");
        }
        }

        private List<ReferralRecord> createUserListWithoutId(){
            String customerId1 = "fakecustomerid1";
            String customerId2 = "fakecustomerid2";
            String customerId3 = "fakecustomerid3";
            String customerId4 = "fakecustomerid4";
            List<ReferralRecord> usersWithoutIds = new ArrayList<>();

            ReferralRecord record1 = new ReferralRecord();
            record1.setCustomerId(customerId1);
            usersWithoutIds.add(record1);

            ReferralRecord record2 = new ReferralRecord();
            record2.setCustomerId(customerId2);
            usersWithoutIds.add(record2);

            ReferralRecord record3 = new ReferralRecord();
            record3.setCustomerId(customerId3);
            usersWithoutIds.add(record3);

            ReferralRecord record4 = new ReferralRecord();
            record4.setCustomerId(customerId4);
            usersWithoutIds.add(record4);

            return usersWithoutIds;
        }
        private List<ReferralRecord> createReferralRecords(String customerId){
            List<ReferralRecord> recordList = new ArrayList<>();


            String customerId1 = "fakecustomerid1";
            if (customerId.equals(customerId1)) {
                ReferralRecord record1_1 = new ReferralRecord();
                record1_1.setCustomerId("customer5");
                record1_1.setReferrerId(customerId1);
                record1_1.setDateReferred(ZonedDateTime.now());
                recordList.add(record1_1);

                ReferralRecord record1_2 = new ReferralRecord();
                record1_2.setCustomerId("customer6");
                record1_2.setReferrerId(customerId1);
                record1_2.setDateReferred(ZonedDateTime.now());
                recordList.add(record1_2);

                ReferralRecord record1_3 = new ReferralRecord();
                record1_3.setCustomerId("customer7");
                record1_3.setReferrerId(customerId1);
                record1_3.setDateReferred(ZonedDateTime.now());
                recordList.add(record1_3);
                return recordList;
            }


            String customerId2 = "fakecustomerid2";
           if (customerId.equals(customerId2)){
               ReferralRecord record2_1 = new ReferralRecord();
               record2_1.setCustomerId("customer8");
               record2_1.setReferrerId(customerId2);
               record2_1.setDateReferred(ZonedDateTime.now());
               recordList.add(record2_1);

               ReferralRecord record2_2 = new ReferralRecord();
               record2_2.setCustomerId("customer9");
               record2_2.setReferrerId(customerId2);
               record2_2.setDateReferred(ZonedDateTime.now());
               recordList.add(record2_2);

               return recordList;
           }

            String customerId3 = "fakecustomerid3";
           if (customerId.equals(customerId3)) {
               ReferralRecord record3_1 = new ReferralRecord();
               record3_1.setCustomerId("customer10");
               record3_1.setReferrerId(customerId3);
               record3_1.setDateReferred(ZonedDateTime.now());
               recordList.add(record3_1);
            return recordList;
           }

            return recordList;
        }

    }

