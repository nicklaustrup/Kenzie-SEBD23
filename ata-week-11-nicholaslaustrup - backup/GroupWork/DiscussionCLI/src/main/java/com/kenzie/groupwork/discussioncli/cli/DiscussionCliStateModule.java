package com.kenzie.groupwork.discussioncli.cli;

import dagger.Module;
import dagger.Provides;

@Module
public class DiscussionCliStateModule {
@Provides
    public DiscussionCliState provideDiscussionCliState(){
        return new DiscussionCliState();
    }
}
