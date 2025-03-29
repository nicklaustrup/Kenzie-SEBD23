package com.kenzie.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kenzie.activity.converter.ZonedDateTimeConverter;
import com.kenzie.activity.dao.EventAnnouncementDao;
import com.kenzie.activity.dao.models.Event;
import com.kenzie.activity.dao.models.EventAnnouncement;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetEventAnnouncementsBetweenDatesActivity {
    private EventAnnouncementDao eventAnnouncementDao;

    /**
     * Constructs an Activity with the given DAO.
     * @param eventAnnouncementDao The EventDao to use for fetching event
     */
    @Inject
    public GetEventAnnouncementsBetweenDatesActivity(EventAnnouncementDao eventAnnouncementDao) {
        this.eventAnnouncementDao = eventAnnouncementDao;
    }

    /**
     * Gets event announcements between the given startTime and endTime (inclusive).
     *
     * NOTE: A little deviation from usual.
     * Here we're using values directly in our arguments and return value,
     * whereas in a typical Coral service we'd have Request/Result objects
     * that would be generated from configuration via Coral. We haven't
     * created service infrastructure for this activity, so we're just
     * using the values directly.
     *
     * @param eventId The ID of the event to get
     * @param startTime The beginning of time range to get announcements for
     * @param endTime The end of time range to get announcements for
     * @return list of announcements posted in the given time range for the event
     */
    public List<EventAnnouncement> handleRequest(final String eventId,
                                                 final ZonedDateTime startTime,
                                                 final ZonedDateTime endTime) {
        return eventAnnouncementDao.getEventAnnouncementsBetweenDates(eventId, startTime, endTime);
    }
}
