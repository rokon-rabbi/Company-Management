package com.company.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Positive(message = "Salary must be positive")
    private Double salary;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "department_id")
    private Department department;
}
