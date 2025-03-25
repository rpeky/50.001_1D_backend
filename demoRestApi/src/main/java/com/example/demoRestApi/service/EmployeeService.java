package com.example.demoRestApi.service;

import com.example.demoRestApi.model.Employee;

import java.util.List;

public interface EmployeeService {
    public String createEmployee(Employee employee);
    public String updateEmployee(Employee employee);
    public String deleteEmployee(int employee_id);
    public Employee getEmployee(int employee_id);
    public List<Employee> getAllEmployees();
}
