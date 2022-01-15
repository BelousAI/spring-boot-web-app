package com.belous.springboot.thymeleafwebappdemo.service;

import com.belous.springboot.thymeleafwebappdemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    void save(Employee theEmployee);
}
