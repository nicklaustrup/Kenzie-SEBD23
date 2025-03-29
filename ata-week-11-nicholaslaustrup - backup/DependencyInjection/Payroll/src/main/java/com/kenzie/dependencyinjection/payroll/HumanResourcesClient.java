package com.kenzie.dependencyinjection.payroll;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Represents the client that connects to our HR system.
 */
public class HumanResourcesClient {

    @Inject
    public HumanResourcesClient() { }

    /**
     * Returns the next five employees on our company payroll.
     *
     * @return the next five employees on our company payroll.
     */
    public List<Employee> getNextPayrollEmployees() {
        List<Employee> payroll = new ArrayList<>();
        payroll.add(new Employee(calculateSalary()));
        payroll.add(new Employee(calculateSalary()));
        payroll.add(new Employee(calculateSalary()));
        payroll.add(new Employee(calculateSalary()));
        payroll.add(new Employee(calculateSalary()));

        return payroll;
    }

    /**
     * Calculates an employee's salary.
     * @return The calculated salary.
     */
    private BigDecimal calculateSalary() {
        return BigDecimal.valueOf((Math.random() * 1000) + 100);
    }
}
