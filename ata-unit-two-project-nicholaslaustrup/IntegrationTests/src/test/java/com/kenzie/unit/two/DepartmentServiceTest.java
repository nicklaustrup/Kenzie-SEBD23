package com.kenzie.unit.two;

import com.kenzie.unit.two.iam.entities.Departments;
import com.kenzie.unit.two.iam.lambda.models.CreateDepartmentRequest;
import com.kenzie.unit.two.iam.models.Department;
import com.kenzie.unit.two.iam.service.DepartmentService;
import com.kenzie.unit.two.iam.storage.Storage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class DepartmentServiceTest {
    @Mock(name="storage")
    Storage storage;
    @InjectMocks
    DepartmentService departmentService;
    String departmentName;
    CreateDepartmentRequest createDepartmentRequest;

    @BeforeEach
    void beforeEach(){
        initMocks(this);
        System.out.println(":)");
        departmentService = new DepartmentService(storage);
        departmentName = RandomStringUtils.random(20);
        createDepartmentRequest = new CreateDepartmentRequest();
        createDepartmentRequest.setDepartmentName(departmentName);
    }

    @AfterEach
    void afterEach() {
        System.out.println(":)");
    }

    @Test
    void createNewDepartment_TASK_7() {
        when(storage.getDepartmentByName(departmentName)).thenReturn(null);

        //THEN
        Department department = departmentService.createDepartment(createDepartmentRequest);
        assertTrue(department.getId() != null);
    }

    @Test
    void throwExceptionDepartmentNameAlreadyExists_TASK_7() {
        departmentService.createDepartment(createDepartmentRequest);

        when(storage.getDepartmentByName(createDepartmentRequest.getDepartmentName())).thenThrow(IllegalArgumentException.class);


        //THEN
        assertThrows(IllegalArgumentException.class,
                () -> departmentService.createDepartment(createDepartmentRequest));
    }

    @Test
    void getDepartmentByName_TASK_7(){
        when(storage.getDepartmentByName(createDepartmentRequest.getDepartmentName())).thenReturn(null);
        Department department = departmentService.createDepartment(createDepartmentRequest);

        when(storage.getDepartmentByName(departmentName)).thenReturn(department);
        //THEN
        Department queriedDepartment = departmentService.getDepartmentByName(departmentName);
        assertTrue(department.getId().equals(queriedDepartment.getId()));
    }

    @Test
    void getAllDepartments(){
        List<Department> departmentList = new ArrayList<>();
        when(storage.getDepartmentByName(createDepartmentRequest.getDepartmentName())).thenReturn(null);

        Department department = departmentService.createDepartment(createDepartmentRequest);
        departmentList.add(department);

        when(storage.getDepartments()).thenReturn(departmentList);

        //THEN
        List<Department> departments = departmentService.getDepartments();
        assertTrue(departments.contains(department));
        verify(storage).getDepartments();
    }
}