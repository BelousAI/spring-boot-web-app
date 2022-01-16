package com.belous.springboot.thymeleafwebappdemo.controller;

import com.belous.springboot.thymeleafwebappdemo.entity.Employee;
import com.belous.springboot.thymeleafwebappdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // add mapping for "/showFormForAdd"
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    // add mapping for "/showFormForUpdate"
    @GetMapping("/showFormForUpdate")
    public String updateEmployee(@RequestParam("employeeId") int theId, Model theModel) {

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form
        return "/employees/employee-form";
    }

    // add mapping for "/save"
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submission
        return "redirect:/employees/list";
    }
}
