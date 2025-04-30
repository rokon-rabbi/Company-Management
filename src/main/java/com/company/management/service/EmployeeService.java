package com.company.management.service;

import com.company.management.entity.Employee;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees(Pageable pageable);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    List<Employee> getEmployeesByDepartmentId(Long departmentId);
}
