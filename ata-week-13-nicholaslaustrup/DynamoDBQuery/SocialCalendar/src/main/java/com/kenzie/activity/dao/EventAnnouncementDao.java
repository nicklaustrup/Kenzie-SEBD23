package com.kenzie.activity.dao;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kenzie.activity.converter.ZonedDateTimeConverter;
import com.kenzie.activity.dao.models.Event;
import com.kenzie.activity.dao.models.EventAnnouncement;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages access to EventAnnouncement items.
 */
public class EventAnnouncementDao {

    private DynamoDBMapper mapper;

    /**
     * Creates an EventDao with the given DDB mapper.
     * @param mapper DynamoDBMapper
     */
    @Inject
    public EventAnnouncementDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Gets all event announcements for a specific event.
     *
     * @param eventId The event to get announcements for.
     * @return the list of event announcements.
     */
    public List<EventAnnouncement> getEventAnnouncements(String eventId) {
        EventAnnouncement eventAnnouncement = new EventAnnouncement();
        eventAnnouncement.setEventId(eventId);

        DynamoDBQueryExpression<EventAnnouncement> queryExpression = new DynamoDBQueryExpression<EventAnnouncement>()
                .withHashKeyValues(eventAnnouncement);

       PaginatedQueryList<EventAnnouncement> eventAnnouncementPaginatedQueryList = mapper.query(EventAnnouncement.class, queryExpression);

        return eventAnnouncementPaginatedQueryList;

    }

    /**
     * Get all event announcements posted between the given dates for the given event.
     *
     * @param eventId The event to get announcements for.
     * @param startTime The start time to get announcements for.
     * @param endTime The end time to get announcements for.
     * @return The list of event announcements.
     */
    public List<EventAnnouncement> getEventAnnouncementsBetweenDates(String eventId, ZonedDateTime startTime,
                                                                     ZonedDateTime endTime) {
        //convert ZonedDateTime into Strings
        ZonedDateTimeConverter converter = new ZonedDateTimeConverter();
        String startDate = converter.convert(startTime);
        String endDate = converter.convert(endTime);

        //Attribute map for narrowing query
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":eventId", new AttributeValue().withS(eventId));
        valueMap.put(":startDate", new AttributeValue().withS(startDate));
        valueMap.put(":endDate", new AttributeValue().withS(endDate));

        DynamoDBQueryExpression<EventAnnouncement> queryExpression = new DynamoDBQueryExpression<EventAnnouncement>()
                .withKeyConditionExpression("eventId = :eventId and timePublished between :startDate and :endDate")
                .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<EventAnnouncement> eventListBetweenDates = mapper.query(EventAnnouncement.class, queryExpression);

        return eventListBetweenDates;
    }

    /**
     * Creates a new event announcement.
     *
     * @param eventAnnouncement The event announcement to create.
     * @return The newly created event announcement.
     */
    public EventAnnouncement createEventAnnouncement(EventAnnouncement eventAnnouncement) {
        mapper.save(eventAnnouncement);
        return eventAnnouncement;
    }
}
