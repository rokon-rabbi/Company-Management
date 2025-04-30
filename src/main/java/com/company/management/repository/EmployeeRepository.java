package com.company.management.repository;

import com.company.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.department.id = :deptId")
    List<Employee> findAllEmployeesByDepartmentId(@Param("deptId") Long deptId);
    Optional<Employee> findByEmail(String email);
}
