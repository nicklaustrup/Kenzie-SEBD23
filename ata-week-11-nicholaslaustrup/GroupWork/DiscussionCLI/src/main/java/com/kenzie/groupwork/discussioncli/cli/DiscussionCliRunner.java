package com.kenzie.groupwork.discussioncli.cli;

import com.kenzie.groupwork.discussioncli.dynamodb.DynamoDbClientProvider;
import com.kenzie.groupwork.discussioncli.dynamodb.MemberDao;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicDao;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicMessageDao;
import com.kenzie.groupwork.discussioncli.handler.ChangeTopicHandler;
import com.kenzie.groupwork.discussioncli.handler.CreateTopicHandler;
import com.kenzie.groupwork.discussioncli.handler.CreateTopicMessageHandler;
import com.kenzie.groupwork.discussioncli.handler.ExitHandler;
import com.kenzie.groupwork.discussioncli.handler.LoginHandler;
import com.kenzie.groupwork.discussioncli.handler.ViewTopicMessagesHandler;
import com.kenzie.groupwork.discussioncli.handler.ViewTopicsHandler;
import com.kenzie.groupwork.discussioncli.cli.input.ATAUserInput;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliComponent;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import dagger.*;

/**
 * Provides a main method to instantiate and run the DiscussionCli we will be
 * working with in this lesson.
 */
public class DiscussionCliRunner {

    /**
     * Starts the CLI application.
     *
     * @param args no args expected
     */
    public static void main(String[] args) {
        DiscussionCliComponent dagger = DaggerDiscussionCliComponent.create();
        DiscussionCli cli = dagger.provideDiscussionCli();

        cli.handleRequests();
    }
}