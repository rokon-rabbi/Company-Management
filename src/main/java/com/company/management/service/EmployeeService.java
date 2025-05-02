package com.company.management.service;

import com.company.management.entity.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Page<Employee> getAllEmployees(Pageable pageable);
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    List<Employee> getEmployeesByDepartmentId(Long departmentId);
}

