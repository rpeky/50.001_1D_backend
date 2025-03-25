package com.example.demoRestApi.service.impl;

import com.example.demoRestApi.model.Employee;
import com.example.demoRestApi.repository.EmployeeRepository;
import com.example.demoRestApi.service.EmployeeService;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    //employeeRepository is a package-private attribute
    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public String createEmployee(Employee employee) {
        // More Business logic to write
        employeeRepository.save(employee);
        return "Success";
    }

    @Override
    public String updateEmployee(Employee employee) {
        // More Business logic to write
        employeeRepository.save(employee);
        return "Success";
    }

    @Override
    public String deleteEmployee(int employee_id) {
        // More Business logic to write
        employeeRepository.deleteById(employee_id);
        return "Success";
    }

    @Override
    public Employee getEmployee(int employee_id) {
        // More Business logic to write
        return employeeRepository.findById(employee_id).get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        // More Business logic to write
        return employeeRepository.findAll();
    }
}
