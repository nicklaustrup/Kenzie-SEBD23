package com.kenzie.groupwork.discussioncli.handler;

import com.kenzie.groupwork.discussioncli.cli.DiscussionCliOperation;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliState;
import com.kenzie.groupwork.discussioncli.cli.input.ATAUserInput;

import javax.inject.Inject;

/**
 * Handler for the CREATE_TOPIC operation.
 */
public class CreateTopicMessageHandler implements DiscussionCliOperationHandler {
    private ATAUserInput userHandler;
    private DiscussionCliState state;

    /**
     * Constructs handler with its dependencies.
     * @param userHandler the ATAUserInput, for user input
     */
    @Inject
    public CreateTopicMessageHandler(ATAUserInput userHandler, DiscussionCliState state) {
        this.userHandler = userHandler;
        this.state = state;
    }

    @Override
    public String handleRequest() {
        if (null == state.getCurrentMember()) {
            throw new IllegalStateException(
                "Encountered request to create topic message but there is no current member. Exiting"
            );
        }
        if (null == state.getCurrentTopic()) {
            state.setNextOperation(DiscussionCliOperation.VIEW_TOPICS);
            return "You must select a topic first.";
        }

        String messageContent = userHandler.getString("Message:");
        // PARTICIPANTS: Create the TopicMessage and save it
        state.setNextOperation(DiscussionCliOperation.VIEW_TOPIC_MESSAGES);


        return "\n*** Creating topic messages isn't supported yet. Maybe you can implement it? :)";
    }
}
