package com.kenzie.dependencyinjection.payroll;

import java.math.BigDecimal;
import javax.inject.Inject;

public class EmployeePaymentDistributor {

    private PayrollTracker payrollTracker;

    /**
     * Constructor for Employee class.
     */
    @Inject
    public EmployeePaymentDistributor(PayrollTracker payrollTracker) {
        this.payrollTracker = payrollTracker;
    }

    /**
     * Pay an employee.
     * @param employee The employee to pay
     * @return the current updated of the Payroll account
     */
    public BigDecimal payEmployee(Employee employee) {
        return payrollTracker.payEmployee(employee);
    }

    /**
     * Check if we have paid an employee.
     * @param employee the employee to check if we have paid
     * @return true if the employee has been paid, false otherwise
     */
    public boolean employeeHasBeenPaid(Employee employee) {
        return payrollTracker.getPaidEmployees().contains(employee);
    }
}
