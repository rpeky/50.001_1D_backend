package com.example.demoRestApi.controller;

import com.example.demoRestApi.model.Employee;
import com.example.demoRestApi.service.impl.EmployeeServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    EmployeeServiceImpl employeeService;

    public EmployeesController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    // Read specific Employees
    @GetMapping("{employee_id}")
    public Employee getEmployeeDetails(@PathVariable("employee_id") String employee_id) {
        int employee_id_int = Integer.parseInt(employee_id); //might have to put into service layer
        return this.employeeService.getEmployee(employee_id_int);
    }

    // Read all Employees
    @GetMapping("/all")
    public List<Employee> getAllEmployeeDetails() {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping
    public String createEmployeeDetails(@RequestBody Employee employee) {
        this.employeeService.createEmployee(employee);
        return "Employee Created Successfully";
    }

    @PutMapping
    public String updateEmployeeDetails(@RequestBody Employee employee) {
        this.employeeService.updateEmployee(employee);
        return "Employee Updated Successfully";
    }

    @DeleteMapping("{employee_id}")
    public String deleteEmployeeDetails(@PathVariable("employee_id") String employee_id) {
        int employee_id_int = Integer.parseInt(employee_id); //might have to put into service layer
        this.employeeService.deleteEmployee(employee_id_int);
        return "Employee Deleted Successfully";
    }


}
