package com.kenzie.inmemorycaching.kenziegaming.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.inmemorycaching.kenziegaming.activity.CreateGroupActivity;
import com.kenzie.inmemorycaching.kenziegaming.dao.models.Group;
import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupMembership;
import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupMembershipCacheKey;
import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class GroupMembershipCachingDaoTest {
    @Mock
    private GroupMembershipDao membershipDao;

    // The unit under test
    @InjectMocks
    private GroupMembershipCachingDao cachingMembershipDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    // Rename this method
    @Test
    public void isUserInGroup_returnsCache() {
        // Implement your test here
        // GIVEN
        GroupMembership membership = new GroupMembership();
        membership.setGroupId(UUID.randomUUID().toString());
        membership.setUserId(UUID.randomUUID().toString());
        membership.setGroupType(GroupType.DISCUSSION_GROUP);

        Group group = new Group();
        group.setId(membership.getGroupId());
        group.setName("testname");
        group.setType(GroupType.DISCUSSION_GROUP);

        GroupMembershipCacheKey cacheKey = new GroupMembershipCacheKey(membership.getUserId(), group.getId());


        // WHEN
        when(membershipDao.isUserInGroup(cacheKey)).thenReturn(true);
        Boolean isUserInGroup = cachingMembershipDao.isUserInGroup(membership.getUserId(), membership.getGroupId());

        // THEN

        verify(membershipDao, times(1)).isUserInGroup(cacheKey);
        assertTrue(isUserInGroup);
    }

    // Rename this method
    @Test
    public void isUserInGroup_returnsFalse() {
        // Implement your test here
        // GIVEN
        GroupMembership membership = new GroupMembership();
        membership.setGroupId(UUID.randomUUID().toString());
        membership.setUserId(UUID.randomUUID().toString());
        membership.setGroupType(GroupType.DISCUSSION_GROUP);

        Group group = new Group();
        group.setId(UUID.randomUUID().toString());
        group.setName("testname");
        group.setType(GroupType.GAMING_GROUP);

        GroupMembershipCacheKey cacheKey = new GroupMembershipCacheKey(membership.getUserId(), group.getId());


        // WHEN
        when(membershipDao.isUserInGroup(cacheKey)).thenReturn(false);
        Boolean isUserInGroup = cachingMembershipDao.isUserInGroup(cacheKey.getUserId(), cacheKey.getGroupId());

        // THEN

        verify(membershipDao, times(1)).isUserInGroup(cacheKey);
        assertFalse(isUserInGroup);
    }
}
