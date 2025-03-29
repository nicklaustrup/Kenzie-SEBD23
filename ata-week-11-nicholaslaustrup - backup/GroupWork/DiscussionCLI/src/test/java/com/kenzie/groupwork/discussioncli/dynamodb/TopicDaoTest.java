package com.kenzie.groupwork.discussioncli.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TopicDaoTest {
    @InjectMocks
    private TopicDao topicDao;

    @Mock
    private DynamoDBMapper mapper;

    @BeforeEach
    public void setup() {
        initMocks(this);
//        topicDao = new TopicDao(mapper);
    }

    @Test
    void createTopic_withValidTopic_isSavedAndReturned() {
        // GIVEN
        // topic to be saved
        Topic newTopic = new Topic("my topic name", "my topic description");
        // DynamoDB accepts it
        doNothing().when(mapper).save(any());

        // WHEN
        Topic result = topicDao.createTopic(newTopic);

        // THEN
        assertEquals(result, newTopic);
    }
}
