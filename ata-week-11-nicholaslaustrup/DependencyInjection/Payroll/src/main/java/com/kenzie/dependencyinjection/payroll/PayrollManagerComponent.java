package com.kenzie.dependencyinjection.payroll;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {BankClientModule.class})
interface PayrollManagerComponent {
    EmployeePaymentDistributor provideEmployeePaymentDistributor();
    HumanResourcesClient provideHumanResourcesClient();
}
