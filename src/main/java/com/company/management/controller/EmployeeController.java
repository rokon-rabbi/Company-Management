package com.company.management.controller;

import com.company.management.entity.Employee;
import com.company.management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee emp) {
        return new ResponseEntity<>(service.createEmployee(emp), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @GetMapping
    public Page<Employee> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return service.getAllEmployees(pageable);  // ✔ Match signature
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody Employee emp) {
        return service.updateEmployee(id, emp);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEmployee(id);
    }

    @GetMapping("/department/{deptId}")
    public List<Employee> getEmployeesByDepartment(@PathVariable Long deptId) {
        return service.getEmployeesByDepartmentId(deptId);
    }
}
