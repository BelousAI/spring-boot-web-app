package com.belous.springboot.thymeleafwebappdemo.service;

import com.belous.springboot.thymeleafwebappdemo.entity.user.User;
import com.belous.springboot.thymeleafwebappdemo.user.DirUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(DirUser dirUser);
}
