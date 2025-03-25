package com.example.demoRestApi.model;

import java.time.LocalDate;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private int employee_id;
    private String first_name;
    private String last_name;
    private double hourly_pay;
    private LocalDate hire_date;

    public Employee(int employee_id, String first_name, String last_name, double hourly_pay, LocalDate date) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hourly_pay = hourly_pay;
        this.hire_date = date;
    }

    public Employee() {
    }


    public int getEmployee_id() {
        return employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public double getHourly_pay() {
        return hourly_pay;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setHourly_pay(double hourly_pay) {
        this.hourly_pay = hourly_pay;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }


}
