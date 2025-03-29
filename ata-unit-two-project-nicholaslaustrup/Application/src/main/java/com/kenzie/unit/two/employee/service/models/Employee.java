package com.kenzie.unit.two.employee.service.models;

import com.kenzie.ata.ExcludeFromJacocoGeneratedReport;
import com.kenzie.unit.two.iam.models.Department;
import com.kenzie.unit.two.iam.models.User;

import java.util.Objects;
import java.util.UUID;

@ExcludeFromJacocoGeneratedReport
public class Employee extends User {
    private final String payCheck;


    public Employee(UUID id, String userName, Department department, String payCheck) {
    super(id, userName, department);
        this.payCheck = payCheck;
    }

    public String getPayCheck() {
        return payCheck;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
