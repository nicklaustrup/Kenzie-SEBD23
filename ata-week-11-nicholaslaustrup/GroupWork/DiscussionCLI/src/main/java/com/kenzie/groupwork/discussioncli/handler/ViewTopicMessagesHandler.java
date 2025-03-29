package com.kenzie.groupwork.discussioncli.handler;

import com.kenzie.groupwork.discussioncli.cli.DiscussionCliOperation;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliState;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicMessage;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicMessageDao;
import com.kenzie.groupwork.discussioncli.cli.input.TextTable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handler for the VIEW_TOPIC operation.
 */
public class ViewTopicMessagesHandler implements DiscussionCliOperationHandler {
    private TopicMessageDao topicMessageDao;
    private DiscussionCliState state;

    /**
     * Constructs handler with its dependencies.
     * @param topicMessageDao TopicMessageDao
     */
    @Inject
    public ViewTopicMessagesHandler(TopicMessageDao topicMessageDao, DiscussionCliState state) {
        this.topicMessageDao = topicMessageDao;
        this.state = state;
    }

    @Override
    public String handleRequest() {
        if (null == state.getCurrentTopic()) {
            state.setNextOperation(DiscussionCliOperation.VIEW_TOPICS);
            return "Sorry, you must select a topic first.";
        }

        String topicName = state.getCurrentTopic().getName();
        List<TopicMessage> topicMessages = topicMessageDao.getMessagesForTopicName(topicName);
        state.setListedTopicMessages(topicMessages);
        return renderTopicMessages(topicMessages);
    }

    private String renderTopicMessages(List<TopicMessage> topicMessages) {
        List<String> headers = Arrays.asList("#", "Author", "Timestamp", "Message");
        List<List<String>> rows = new ArrayList<>();
        int messageNum = 0;
        for (TopicMessage topicMessage : topicMessages) {
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(messageNum++));
            row.add(topicMessage.getAuthor());
            row.add(topicMessage.getTimestamp());
            row.add(topicMessage.getMessageContent());
            rows.add(row);
        }

        return new TextTable(headers, rows).toString();
    }
}
