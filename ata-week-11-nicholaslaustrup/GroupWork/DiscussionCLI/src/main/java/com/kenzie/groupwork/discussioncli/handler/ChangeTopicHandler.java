package com.kenzie.groupwork.discussioncli.handler;

import com.kenzie.groupwork.discussioncli.cli.DiscussionCliOperation;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliState;
import com.kenzie.groupwork.discussioncli.dynamodb.Topic;
import com.kenzie.groupwork.discussioncli.cli.input.ATAUserInput;

import javax.inject.Inject;

/**
 * Handles the CHANGE_TOPIC operation.
 */
public class ChangeTopicHandler implements DiscussionCliOperationHandler {
    private ATAUserInput userHandler;
    private DiscussionCliState state;

    /**
     * Constructs handler with its dependencies.
     * @param userHandler the ATAUserInput, for user input
     */
    @Inject
    public ChangeTopicHandler(ATAUserInput userHandler, DiscussionCliState state) {
        this.userHandler = userHandler;
        this.state = state;
    }

    @Override
    public String handleRequest() {
        int topicNumber = userHandler.getInteger(0, state.getListedTopics().size() - 1, "Select topic number:");
        Topic nextTopic = state.getListedTopics().get(topicNumber);
        state.setCurrentTopic(nextTopic);
        state.setNextOperation(DiscussionCliOperation.VIEW_TOPIC_MESSAGES);

        return String.format("Topic changed to '%s'", nextTopic.getName());
    }
}
