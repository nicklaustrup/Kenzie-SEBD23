package com.kenzie.groupwork.discussioncli.cli;

import com.kenzie.groupwork.discussioncli.dynamodb.Member;
import com.kenzie.groupwork.discussioncli.dynamodb.Topic;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicMessage;
import dagger.Module;

import java.util.List;

/**
 * Contains the runtime state, particularly user, current/available topic/s, and
 * next operation, for an instance of DiscussionCli.
 */
@Module
public class DiscussionCliState {
    private Member currentMember;
    private Topic currentTopic;
    private List<Topic> listedTopics;
    private List<TopicMessage> listedTopicMessages;
    private DiscussionCliOperation nextOperation;

    public Member getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }

    public Topic getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentTopic(Topic currentTopic) {
        this.currentTopic = currentTopic;
    }

    public List<Topic> getListedTopics() {
        return listedTopics;
    }

    public void setListedTopics(List<Topic> listedTopics) {
        this.listedTopics = listedTopics;
    }

    public List<TopicMessage> getListedTopicMessages() {
        return listedTopicMessages;
    }

    public void setListedTopicMessages(List<TopicMessage> listedTopicMessages) {
        this.listedTopicMessages = listedTopicMessages;
    }

    public DiscussionCliOperation getNextOperation() {
        return nextOperation;
    }

    public void setNextOperation(DiscussionCliOperation nextOperation) {
        this.nextOperation = nextOperation;
    }

    @Override
    public String toString() {
        return "DiscussionCliState{" +
               "currentMember=" + currentMember +
               ", currentTopic=" + currentTopic +
               ", listedTopics=" + listedTopics +
               ", listedTopicMessages=" + listedTopicMessages +
               ", nextOperation=" + nextOperation +
               '}';
    }
}
