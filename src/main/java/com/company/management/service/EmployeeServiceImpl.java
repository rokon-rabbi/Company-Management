package com.company.management.service;

import com.company.management.entity.Department;
import com.company.management.entity.Employee;
import com.company.management.exception.DuplicateEmailException;
import com.company.management.exception.ResourceNotFoundException;
import com.company.management.repository.DepartmentRepository;
import com.company.management.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new DuplicateEmailException("Email already exists: " + employee.getEmail());
        }

        Department department = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = getEmployeeById(id);

        if (!employee.getEmail().equals(updatedEmployee.getEmail()) &&
                employeeRepository.findByEmail(updatedEmployee.getEmail()).isPresent()) {
            throw new DuplicateEmailException("Email already exists: " + updatedEmployee.getEmail());
        }

        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setSalary(updatedEmployee.getSalary());

        Department department = departmentRepository.findById(updatedEmployee.getDepartment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department not found");
        }
        return employeeRepository.findAllEmployeesByDepartmentId(departmentId);
    }
}
