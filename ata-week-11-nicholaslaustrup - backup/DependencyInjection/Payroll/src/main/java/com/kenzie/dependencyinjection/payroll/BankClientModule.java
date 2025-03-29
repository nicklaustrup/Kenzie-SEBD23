package com.kenzie.dependencyinjection.payroll;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class BankClientModule {
    @Provides
    @Singleton
    public BankClient provideBankClient() {
        return new BankClient();
    }
}
