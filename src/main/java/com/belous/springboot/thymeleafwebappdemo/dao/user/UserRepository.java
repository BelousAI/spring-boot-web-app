package com.belous.springboot.thymeleafwebappdemo.dao.user;

import com.belous.springboot.thymeleafwebappdemo.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

}
