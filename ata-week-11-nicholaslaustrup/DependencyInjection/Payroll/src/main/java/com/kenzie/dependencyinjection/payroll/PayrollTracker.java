package com.kenzie.dependencyinjection.payroll;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;


public class PayrollTracker {
    private BankClient bankClient;
    private Set<Employee> paidEmployees = new HashSet<>();

    /**
     * Constructor for SalaryInformation class.
     */
    @Inject
    public PayrollTracker(BankClient bankClient) {
        // create our third party banking client
        this.bankClient = bankClient;
    }

    /**
     * Method that withdraws an employee's salary amount from an account
     * retrieved from the bankClient.
     *
     * @param employee The employee that will get paid
     * @return the updated balance after paying the provided Employee.
     */
    public BigDecimal payEmployee(Employee employee) {
        BigDecimal balance =  bankClient.withdraw(employee.getSalary());
        paidEmployees.add(employee);

        return balance;
    }

    public Set<Employee> getPaidEmployees() {
        return paidEmployees;
    }
}
