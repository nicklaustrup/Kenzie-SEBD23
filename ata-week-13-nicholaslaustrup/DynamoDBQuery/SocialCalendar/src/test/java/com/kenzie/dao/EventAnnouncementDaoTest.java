package com.kenzie.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.kenzie.activity.dao.EventAnnouncementDao;
import com.kenzie.activity.dao.models.EventAnnouncement;
import com.kenzie.activity.dao.models.Invite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class EventAnnouncementDaoTest {
    private static final String TEST_EVENT_ID = "eventId";
    private static final String TEST_MEMBER_ID = "memberId";
    @Mock
    DynamoDBMapper mapper;

    @Mock
    PaginatedQueryList<EventAnnouncement> queryResult;

    @InjectMocks
    EventAnnouncementDao eventAnnouncementDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getEventAnnouncements_queriesDynamoDb_returnsListFromDynamo() {
        // GIVEN
        when(mapper.query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);

        // WHEN
        List<EventAnnouncement> results = eventAnnouncementDao.getEventAnnouncements(TEST_EVENT_ID);

        // THEN
        verify(mapper).query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class));
        assertEquals(results, queryResult, "Expected getEventAnnouncements to return the query list");

        //Argument Captor
        ArgumentCaptor<DynamoDBQueryExpression<EventAnnouncement>> captor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        verify(mapper).query(eq(EventAnnouncement.class), captor.capture());
        DynamoDBQueryExpression<EventAnnouncement> capturedQueryExpression = captor.getValue();
        EventAnnouncement queriedInvite = capturedQueryExpression.getHashKeyValues();
        assertEquals(TEST_EVENT_ID, queriedInvite.getEventId(), "Expected query expression to query for " +
                "partition key: " + TEST_EVENT_ID);
        assertNull(capturedQueryExpression.getExclusiveStartKey(), "Expected the exclusive start key map to " +
                "be null when a null exclusive start key is passed in.");
    }

    @Test
    public void getEventAnnouncementsBetweenDates_queriesDynamoDb_returnsListFromDynamo() {
        // GIVEN
        ZonedDateTime start = ZonedDateTime.of(LocalDate.MIN, LocalTime.MIN, ZoneId.of("UTC+1"));
        ZonedDateTime end = ZonedDateTime.of(LocalDate.MAX, LocalTime.MAX, ZoneId.of("UTC+1"));

        when(mapper.query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);

        // WHEN
        List<EventAnnouncement> results = eventAnnouncementDao.getEventAnnouncementsBetweenDates(TEST_EVENT_ID, start, end);

        // THEN
        verify(mapper).query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class));
        assertEquals(results, queryResult, "Expected getEventAnnouncements to return the query list");
    }

}
