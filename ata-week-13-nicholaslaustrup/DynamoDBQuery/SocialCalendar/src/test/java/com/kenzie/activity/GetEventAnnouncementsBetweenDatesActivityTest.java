package com.kenzie.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.kenzie.activity.dao.EventAnnouncementDao;
import com.kenzie.activity.dao.models.EventAnnouncement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetEventAnnouncementsBetweenDatesActivityTest {
    @Mock
    DynamoDBMapper mapper;

    @Mock
    PaginatedQueryList<EventAnnouncement> queryResult;

    @Mock
    EventAnnouncementDao eventAnnouncementDao;

    GetEventAnnouncementsBetweenDatesActivity getBetweenDates;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }
    @Test
    public void getEventAnnouncementsBetweenDates_queriesDynamoDb_returnsListFromDynamo() {
        // GIVEN
        String eventId = "bada bing bada boom";
        getBetweenDates = new GetEventAnnouncementsBetweenDatesActivity(eventAnnouncementDao);
        ZonedDateTime start = ZonedDateTime.of(LocalDate.MIN, LocalTime.MIN, ZoneId.of("UTC+1"));
        ZonedDateTime end = ZonedDateTime.of(LocalDate.MAX, LocalTime.MAX, ZoneId.of("UTC+1"));

        when(mapper.query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);

        // WHEN
        List<EventAnnouncement> results = getBetweenDates.handleRequest(eventId, start, end);

        // THEN
        verify(eventAnnouncementDao).getEventAnnouncementsBetweenDates(eventId, start, end);
    }
}
