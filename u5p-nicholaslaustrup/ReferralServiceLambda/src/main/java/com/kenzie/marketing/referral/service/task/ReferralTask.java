package com.kenzie.marketing.referral.service.task;

import com.kenzie.marketing.referral.model.LeaderboardEntry;
import com.kenzie.marketing.referral.service.dao.ReferralDao;
import com.kenzie.marketing.referral.service.model.ReferralRecord;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.Callable;

public class ReferralTask implements Callable<LeaderboardEntry> {
    private final ReferralDao referralDao;
    private final ReferralRecord record;

    public ReferralTask(ReferralDao referralDao, ReferralRecord record) {
        this.referralDao = referralDao;
        this.record = record;
    }

    @Override
    public LeaderboardEntry call() {

        List<ReferralRecord> referralRecords = referralDao.findByReferrerId(record.getCustomerId());
        LeaderboardEntry leaderBoard = new LeaderboardEntry();
        leaderBoard.setCustomerId(record.getCustomerId());
        leaderBoard.setNumReferrals(referralRecords.size());

        List<ReferralRecord> layerTwo = new ArrayList<>();
        for (ReferralRecord rootCustomer : referralRecords) {
            layerTwo.addAll(referralDao.findByReferrerId(rootCustomer.getCustomerId()));
        }
        leaderBoard.setNumReferrals(leaderBoard.getNumReferrals() + layerTwo.size());

        return leaderBoard;
    }
}
