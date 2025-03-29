package com.kenzie.marketing.referral.service.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.kenzie.marketing.referral.service.caching.CacheClient;
import com.kenzie.marketing.referral.service.dependency.DaggerServiceComponent;
import com.kenzie.marketing.referral.service.model.ReferralRecord;
import redis.clients.jedis.Jedis;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.Ref;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CachingReferralDao implements ReferralDao {

    private static final int REFERRAL_READ_TTL = 60 * 60;
    private static final String REFERRAL_KEY = "ReferralKey::%s";
    private final CacheClient cacheClient;
    private final NonCachingReferralDao referralDao;

    @Inject
    public CachingReferralDao(CacheClient cacheClient, NonCachingReferralDao referralDao) {
        this.cacheClient = cacheClient;
        this.referralDao = referralDao;
    }


    @Override
    public ReferralRecord addReferral(ReferralRecord referralRecord) {
        // Invalidate
        // Add referral to database
        cacheClient.invalidate(referralRecord.getCustomerId());
        return referralDao.addReferral(referralRecord);
    }

    @Override
    public boolean deleteReferral(ReferralRecord referral) {
        //Invalidate
        cacheClient.invalidate(referral.getCustomerId());
        //Delete referral from database
        return referralDao.deleteReferral(referral);
    }

    @Override
    public List<ReferralRecord> findByReferrerId(String referrerId) {
        // Look up data in cache
        Optional<String> valueFromCache = cacheClient.getValue(referrerId);
        // Convert between JSON
        if (valueFromCache.isPresent()) {
            List<ReferralRecord> referralRecords = fromJson(valueFromCache.get());
            return referralRecords;
        }
        // If the data doesn't exist in the cache,
        // Get the data from the data source
        List<ReferralRecord> nonCachedReferrals = referralDao.findByReferrerId(referrerId);
        // Add data to the cache, convert between JSON
        addToCache(nonCachedReferrals);
        return nonCachedReferrals;
    }

    @Override
    public List<ReferralRecord> findUsersWithoutReferrerId() {
        // Look up customer from the data source
        return referralDao.findUsersWithoutReferrerId();
    }

    /****************           HELPER METHODS             *********************/


    // Create the Gson object with instructions for ZonedDateTime
    GsonBuilder builder = new GsonBuilder().registerTypeAdapter(
            ZonedDateTime.class,
            new TypeAdapter<ZonedDateTime>() {
                @Override
                public void write(JsonWriter out, ZonedDateTime value) throws IOException {
                    out.value(value.toString());
                }

                @Override
                public ZonedDateTime read(JsonReader in) throws IOException {
                    return ZonedDateTime.parse(in.nextString());
                }
            }
    ).enableComplexMapKeySerialization();
    // Store this in your class
    Gson gson = builder.create();

    // Converting out of the cache
    private List<ReferralRecord> fromJson(String json) {
        return gson.fromJson(json, new TypeToken<ArrayList<ReferralRecord>>() {
        }.getType());
    }

    // Setting value
    private void addToCache(List<ReferralRecord> records) {
        if (!records.isEmpty()) {
            String referrerId = records.get(0).getReferrerId();
            String cacheKey = String.format(REFERRAL_KEY, referrerId);
            cacheClient.setValue(
                    cacheKey,
                    REFERRAL_READ_TTL,
                    gson.toJson(records)
            );
        }
    }
}
