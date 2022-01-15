package com.belous.springboot.thymeleafwebappdemo.controller;

import com.belous.springboot.thymeleafwebappdemo.entity.Employee;
import com.belous.springboot.thymeleafwebappdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // add mapping for "/list" - return list of employees
    @GetMapping("/list")
    public String getAllEmployees(Model theModel) {

        // get employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // add to Spring MVC model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }
}
