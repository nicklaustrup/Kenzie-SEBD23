package com.kenzie.discussion;


import com.kenzie.discussion.cli.input.ATAUserInput;
import com.kenzie.discussion.cli.DiscussionCliOperation;
import com.kenzie.discussion.cli.DiscussionCliState;
import com.kenzie.discussion.dynamodb.Topic;
import com.kenzie.discussion.handler.ChangeTopicHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ChangeTopicHandlerTest {
    private ChangeTopicHandler handler;
    private DiscussionCliState state;

    @Mock
    private ATAUserInput userHandler;

    @BeforeEach
    public void setup() {
        initMocks(this);
        handler = new ChangeTopicHandler(userHandler);
        state = getState();
    }

    @Test
    void handleRequest_whenUserProvidesTopicNumber_currentTopicIsCorrect() {
        // GIVEN
        // current list of topics
        List<Topic> listedTopics = Arrays.asList(
            new Topic("abc", "def'"),
            new Topic("ghi", "jkl")
        );
        state.setListedTopics(listedTopics);
        // user response
        when(userHandler.getInteger(anyInt(), anyInt(), anyString())).thenReturn(1);
        // expected new topic
        Topic selectedTopic = listedTopics.get(1);

        // WHEN
        String result = handler.handleRequest(state);

        // THEN
        assertTrue(
            result.contains(selectedTopic.getName()),
            String.format(
                "Expected response '%s' to contain topic name '%s'",
                result,
                selectedTopic.getName())
        );
    }

    @Test
    void handleRequest_whenTopicSelected_nextOperationIsViewTopicMessages() {
        // GIVEN
        // current list of topics
        List<Topic> listedTopics = Arrays.asList(
            new Topic("abc", "def'"),
            new Topic("ghi", "jkl")
        );
        state.setListedTopics(listedTopics);
        // user response
        when(userHandler.getInteger(any())).thenReturn(0);

        // WHEN
        handler.handleRequest(state);

        // THEN
        assertEquals(listedTopics.get(0), state.getCurrentTopic());
        assertEquals(DiscussionCliOperation.VIEW_TOPIC_MESSAGES, state.getNextOperation());
    }

    private DiscussionCliState getState() {
        return new DiscussionCliState();
    }
}
