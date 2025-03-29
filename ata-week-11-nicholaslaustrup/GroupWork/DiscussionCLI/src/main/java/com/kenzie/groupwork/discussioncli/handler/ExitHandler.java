package com.kenzie.groupwork.discussioncli.handler;

import com.kenzie.groupwork.discussioncli.cli.DiscussionCliOperation;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliRunner;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliState;

import javax.inject.Inject;

/**
 * Handler for the EXIT operation.
 */
public class ExitHandler implements DiscussionCliOperationHandler {
    private DiscussionCliState state;

    @Inject
    public ExitHandler(DiscussionCliState state){
        this.state = state;
    }
    @Override
    public String handleRequest() {
        this.state.setNextOperation(DiscussionCliOperation.EXIT);
        return "Hope you enjoyed, good-bye!";
    }
}
