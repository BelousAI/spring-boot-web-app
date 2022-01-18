package com.belous.springboot.thymeleafwebappdemo.service;

import com.belous.springboot.thymeleafwebappdemo.dao.user.RoleRepository;
import com.belous.springboot.thymeleafwebappdemo.dao.user.UserRepository;
import com.belous.springboot.thymeleafwebappdemo.entity.user.Role;
import com.belous.springboot.thymeleafwebappdemo.entity.user.User;
import com.belous.springboot.thymeleafwebappdemo.user.DirUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional("securityTransactionManager")
    public User findByUserName(String userName) {
        // check the database if the user already exists
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional("securityTransactionManager")
    public void save(DirUser dirUser) {
        User user = new User();
        // assign user details to the user object
        user.setUserName(dirUser.getUserName());
        user.setPassword(passwordEncoder.encode(dirUser.getPassword()));
        user.setFirstName(dirUser.getFirstName());
        user.setLastName(dirUser.getLastName());
        user.setEmail(dirUser.getEmail());

        // give user default role of "employee"
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_EMPLOYEE")));

        // save user in the database
        userRepository.save(user);
    }

    @Override
    @Transactional("securityTransactionManager")
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
