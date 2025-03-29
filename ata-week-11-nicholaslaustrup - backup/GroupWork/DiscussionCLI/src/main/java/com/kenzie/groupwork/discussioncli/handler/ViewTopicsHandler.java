package com.kenzie.groupwork.discussioncli.handler;

import com.kenzie.groupwork.discussioncli.cli.DiscussionCliState;
import com.kenzie.groupwork.discussioncli.dynamodb.Topic;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicDao;
import com.kenzie.groupwork.discussioncli.cli.input.ATAUserInput;
import com.kenzie.groupwork.discussioncli.cli.input.TextTable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handler for the VIEW_TOPICS operation.
 */
public class ViewTopicsHandler implements DiscussionCliOperationHandler {
    private static final int MAX_NUM_TOPICS_TO_VIEW = 100;

    private final TopicDao topicDao;
    private final ATAUserInput userHandler;
    private DiscussionCliState state;

    /**
     * Constructs handler with its dependencies.
     * @param topicDao The TopicDao
     * @param userHandler the ATAUserInput, for user input
     */
    @Inject
    public ViewTopicsHandler(TopicDao topicDao, ATAUserInput userHandler, DiscussionCliState state) {
        this.topicDao = topicDao;
        this.userHandler = userHandler;
        this.state = state;
    }

    @Override
    public String handleRequest() {
        int numTopics = userHandler.getInteger(
            1,
            MAX_NUM_TOPICS_TO_VIEW,
            "We'll display topics. Select maximum number of topics to view in case there are many: "
        );
        List<Topic> topics = topicDao.getTopics(numTopics);
        state.setListedTopics(topics);
        return renderTopics(topics);
    }

    private String renderTopics(List<Topic> topics) {
        List<String> headers = Arrays.asList("#", "Topic name");
        List<List<String>> rows = new ArrayList<>();
        int topicNum = 0;
        for (Topic topic : topics) {
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(topicNum++));
            row.add(topic.getName());
            rows.add(row);
        }

        return new TextTable(headers, rows).toString();
    }
}
