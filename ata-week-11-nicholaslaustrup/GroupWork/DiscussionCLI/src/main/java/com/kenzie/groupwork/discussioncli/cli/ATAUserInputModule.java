package com.kenzie.groupwork.discussioncli.cli;

import com.kenzie.groupwork.discussioncli.cli.input.ATAUserInput;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ATAUserInputModule {
    @Provides
    public ATAUserInput provideATAUserInput() {
        return new ATAUserInput();
    }
}
