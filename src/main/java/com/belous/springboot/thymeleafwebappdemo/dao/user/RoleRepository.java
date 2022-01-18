package com.belous.springboot.thymeleafwebappdemo.dao.user;

import com.belous.springboot.thymeleafwebappdemo.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
