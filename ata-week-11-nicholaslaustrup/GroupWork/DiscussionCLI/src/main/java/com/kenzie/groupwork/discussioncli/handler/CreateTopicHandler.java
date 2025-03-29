package com.kenzie.groupwork.discussioncli.handler;

import com.kenzie.groupwork.discussioncli.cli.DiscussionCliState;
import com.kenzie.groupwork.discussioncli.dynamodb.Topic;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicDao;
import com.kenzie.groupwork.discussioncli.cli.input.ATAUserInput;

import javax.inject.Inject;

/**
 * Handler for the CREATE_TOPIC operation.
 */
public class CreateTopicHandler implements DiscussionCliOperationHandler {
    private final TopicDao topicDao;
    private final ATAUserInput userHandler;
    private DiscussionCliState state;

    /**
     * Constructs handler with its dependencies.
     * @param topicDao TopicDao
     * @param userHandler the ATAUserInput, for user input
     */
    @Inject
    public CreateTopicHandler(TopicDao topicDao, ATAUserInput userHandler, DiscussionCliState state) {
        this.topicDao = topicDao;
        this.userHandler = userHandler;
        this.state = state;
    }

    @Override
    public String handleRequest() {
        String topicName = userHandler.getString("", "New topic name: ");
        String description = userHandler.getString("", "New topic description: ");

        Topic newTopic = new Topic(topicName, description);
        newTopic = topicDao.createTopic(newTopic);
        state.setCurrentTopic(newTopic);

        return String.format("New topic '%s' was created!%nTopic changed to '%s'",
                             newTopic.getName(), newTopic.getName());
    }
}
