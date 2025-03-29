package com.kenzie.discussion.handler;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.discussion.cli.DiscussionCliRunner;
import com.kenzie.discussion.cli.input.ATAUserInput;
import com.kenzie.discussion.cli.DiscussionCliOperation;
import com.kenzie.discussion.cli.DiscussionCliState;
import com.kenzie.discussion.dynamodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler for the CREATE_TOPIC operation.
 */
public class CreateTopicMessageHandler implements DiscussionCliOperationHandler {
    private ATAUserInput userHandler;

    /**
     * Constructs handler with its dependencies.
     * @param userHandler the ATAUserHandler, for user input
     */
    public CreateTopicMessageHandler(ATAUserInput userHandler) {
        this.userHandler = userHandler;
    }

    @Override
    public String handleRequest(DiscussionCliState state) {
        if (null == state.getCurrentMember()) {
            throw new IllegalStateException(
                "Encountered request to create topic message but there is no current member. Exiting"
            );
        }
        if (null == state.getCurrentTopic()) {
            state.setNextOperation(DiscussionCliOperation.VIEW_TOPICS);
            return "You must select a topic first.";
        }

        String messageContent = userHandler.getString("", "Message:");
        // PARTICIPANTS: Create the TopicMessage and save it
        state.setNextOperation(DiscussionCliOperation.CREATE_TOPIC_MESSAGE);

        TopicMessage newTopicMessage = new TopicMessage();
        newTopicMessage.setMessageContent(messageContent);
        newTopicMessage.setAuthor(state.getCurrentMember().getUsername());
        newTopicMessage.setTopicName(state.getCurrentTopic().getName());

        TopicMessageDao topicMessageDao = new TopicMessageDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient()));
        topicMessageDao.saveMessagesForTopicName(newTopicMessage);

        state.setNextOperation(DiscussionCliOperation.VIEW_TOPIC_MESSAGES);

        return String.format("New topic message '%s' was created!",
                newTopicMessage.getMessageContent());

    }
}
