package com.kenzie.discussion;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.discussion.cli.DiscussionCliState;
import com.kenzie.discussion.cli.input.ATAUserInput;
import com.kenzie.discussion.dynamodb.Member;
import com.kenzie.discussion.dynamodb.Topic;
import com.kenzie.discussion.dynamodb.TopicMessage;
import com.kenzie.discussion.dynamodb.TopicMessageDao;
import com.kenzie.discussion.handler.CreateTopicMessageHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TopicMessageDaoTest {

    TopicMessageDao topicMessageDao;
    @Mock
    DynamoDBMapper mapper;

    @BeforeEach
    public void setup() {
        initMocks(this);
        topicMessageDao = new TopicMessageDao(mapper);
    }

    // PARTICIPANTS - add at least one relevant test (remember to mock DynamoDBMapper!)
    // You can use MemberDaoTest as a guide!

    @Test
    public void setTopicMessageDao_saveTopicMessages_inputSuccess() {
        String topicName = "Beetles";


        String message = "beetles are the best";
        TopicMessage topicMessage = new TopicMessage();
        topicMessage.setTopicName(topicName);
        topicMessage.setMessageContent(message);


        // DynamoDB returns null
        doNothing().when(mapper).save(any());

        topicMessageDao.saveMessagesForTopicName(topicMessage);

        // WHEN
        verify(mapper).save(any());

    }
}
