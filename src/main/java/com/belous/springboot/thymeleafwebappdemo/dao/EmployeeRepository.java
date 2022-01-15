package com.belous.springboot.thymeleafwebappdemo.dao;

import com.belous.springboot.thymeleafwebappdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!
}
