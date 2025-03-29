package com.kenzie.groupwork.discussioncli.cli;

import com.kenzie.groupwork.discussioncli.handler.ChangeTopicHandler;
import com.kenzie.groupwork.discussioncli.handler.CreateTopicHandler;
import com.kenzie.groupwork.discussioncli.handler.CreateTopicMessageHandler;
import com.kenzie.groupwork.discussioncli.handler.ExitHandler;
import com.kenzie.groupwork.discussioncli.handler.LoginHandler;
import com.kenzie.groupwork.discussioncli.handler.ViewTopicMessagesHandler;
import com.kenzie.groupwork.discussioncli.handler.ViewTopicsHandler;
import com.kenzie.groupwork.discussioncli.cli.input.ATAUserInput;

import dagger.Component;
import dagger.Module;

import javax.inject.Singleton;
@Component(modules = {MapperModule.class, ATAUserInputModule.class, DiscussionCliStateModule.class})
@Singleton
public interface DiscussionCliComponent {
DiscussionCli provideDiscussionCli();

}
