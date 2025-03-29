package com.kenzie.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.kenzie.activity.dao.InviteDao;
import com.kenzie.activity.dao.models.Invite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetInvitesForEventActivityTest {
    private static final String TEST_EVENT_ID = "eventId";
    private static final String TEST_MEMBER_ID = "memberId";

    @Mock
    private InviteDao inviteDao;

    @InjectMocks
    GetInvitesForEventActivity activity;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    void handleRequest_withNullExclusiveStartKey_callsDao() {
        // GIVEN && WHEN
        List<Invite> announcements = activity.handleRequest("EVENTID", null);
        // THEN
        verify(inviteDao).getInvitesForEvent("EVENTID", null);
    }

    @Test
    void handleRequest_withExclusiveStartKey_callsDao() {
        // GIVEN && WHEN
        List<Invite> announcements = activity.handleRequest("EVENTID","MEMBERID");

        // THEN
        verify(inviteDao).getInvitesForEvent("EVENTID", "MEMBERID");

    }
}
