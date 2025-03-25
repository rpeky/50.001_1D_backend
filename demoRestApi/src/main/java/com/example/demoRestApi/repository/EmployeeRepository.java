package com.example.demoRestApi.repository;


import com.example.demoRestApi.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

