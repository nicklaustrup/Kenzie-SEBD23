package com.kenzie.dependencyinjection.payroll;

import java.math.BigDecimal;
import java.util.Objects;

public class Employee {
    private BigDecimal salary;

    public Employee(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Returns this employee's salary.
     *
     * @return this employee's salary
     */
    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(getSalary(), employee.getSalary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSalary());
    }
}
