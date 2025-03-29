package com.kenzie.groupwork.discussioncli.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class TopicMessageDaoTest {
    @InjectMocks
    TopicMessageDao topicMessageDao;
    @Mock
    DynamoDBMapper mapper;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

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
