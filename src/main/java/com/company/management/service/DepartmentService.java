package com.company.management.service;

import com.company.management.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();
    Department updateDepartment(Long id, Department department);
    void deleteDepartment(Long id);
    Department getDepartmentWithEmployees(Long id);
}
