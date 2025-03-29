package com.kenzie.inmemorycaching.kenziegaming.dao;

import javax.inject.Inject;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupMembershipCacheKey;

import com.google.common.cache.LoadingCache;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GroupMembershipCachingDao {
    // Create your cache here
    private final LoadingCache<GroupMembershipCacheKey, Boolean> loadingCache;

    @Inject
    public GroupMembershipCachingDao(final GroupMembershipDao delegateDao) {
        loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .maximumSize(4800)
                .build(CacheLoader.from(delegateDao::isUserInGroup));
    }

    public Boolean isUserInGroup(final String userId, final String groupId) {
        return loadingCache.getUnchecked(new GroupMembershipCacheKey(userId, groupId));
    }

}
