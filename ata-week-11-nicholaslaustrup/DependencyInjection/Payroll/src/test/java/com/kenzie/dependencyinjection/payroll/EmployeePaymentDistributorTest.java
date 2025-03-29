package com.kenzie.dependencyinjection.payroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeePaymentDistributorTest {
    @Mock
    private EmployeePaymentDistributor employeePaymentDistributor;
    @Mock
    private PayrollTracker payrollTracker;
    @Mock
    private BankClient bankClient;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void employeeHasBeenPaid_withPaidEmployee_checksThePayrollTrackersSetOfPaidEmployees() {
        // GIVEN a paid employee
        BigDecimal salary = BigDecimal.valueOf(500);
        Employee employee = new Employee(salary);


        //TODO complete mocking this test -- when then is incomplete
        when(employeePaymentDistributor.employeeHasBeenPaid(employee)).thenReturn(true);

        employeePaymentDistributor.payEmployee(employee);

        // WHEN we check if an employee has been paid
        boolean hasBeenPaid = employeePaymentDistributor.employeeHasBeenPaid(employee);

        // THEN assert it is return true
        assertTrue(hasBeenPaid,
                "Expected hasBeenPaid to be true for an employee who has been paid");
    }
}
