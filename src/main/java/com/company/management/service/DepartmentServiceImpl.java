package com.company.management.service;

import com.company.management.entity.Department;
import com.company.management.exception.ResourceNotFoundException;
import com.company.management.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Long id, Department updatedDepartment) {
        Department department = getDepartmentById(id);
        department.setName(updatedDepartment.getName());
        department.setLocation(updatedDepartment.getLocation());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }

    @Override
    public Department getDepartmentWithEmployees(Long id) {
        return getDepartmentById(id); // employees are loaded because of JPA
    }
}
