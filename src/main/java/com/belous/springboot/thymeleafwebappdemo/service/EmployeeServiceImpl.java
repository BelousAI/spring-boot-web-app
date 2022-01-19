package com.belous.springboot.thymeleafwebappdemo.service;

import com.belous.springboot.thymeleafwebappdemo.dao.employee.EmployeeRepository;
import com.belous.springboot.thymeleafwebappdemo.entity.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional("appTransactionManager")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional("appTransactionManager")
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    @Transactional("appTransactionManager")
    public Employee findById(int theId) {

        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Employee id not found - " + theId);
        }
        return theEmployee;
    }

    @Override
    @Transactional("appTransactionManager")
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
