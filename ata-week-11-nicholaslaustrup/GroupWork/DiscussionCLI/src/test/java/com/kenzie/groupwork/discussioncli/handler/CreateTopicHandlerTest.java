package com.kenzie.groupwork.discussioncli.handler;

import com.kenzie.groupwork.discussioncli.cli.DiscussionCliState;
import com.kenzie.groupwork.discussioncli.dynamodb.Topic;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicDao;

import com.kenzie.groupwork.discussioncli.cli.input.ATAUserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateTopicHandlerTest {
    private CreateTopicHandler handler;
    private DiscussionCliState state;

    @Mock
    private TopicDao topicDao;
    @Mock
    private ATAUserInput userHandler;

    @BeforeEach
    public void setup() {
        initMocks(this);
        state = getState();
        handler = new CreateTopicHandler(topicDao, userHandler, state);
    }

    @Test
    void handleRequest_givenTopicAndDescription_createsTopic() {
        // GIVEN
        // Topic data
        String topicName = "name";
        String topicDescription = "description";
        // Topic to create
        Topic newTopic = new Topic(topicName, topicDescription);
        // User handler returns name then description
        when(userHandler.getString("", "New topic name: ")).thenReturn(topicName);
        when(userHandler.getString("", "New topic description: ")).thenReturn(topicDescription);
        // DAO allows creating topic
        when(topicDao.createTopic(any(Topic.class))).thenReturn(newTopic);

        // WHEN
        String result = handler.handleRequest();

        // THEN
        // DAO allows creating topic
        verify(topicDao, times(1)).createTopic(newTopic);
        // Topic name is displayed
        assertTrue(result.contains(newTopic.getName()));
    }

    private DiscussionCliState getState() {
        return new DiscussionCliState();
    }
}
