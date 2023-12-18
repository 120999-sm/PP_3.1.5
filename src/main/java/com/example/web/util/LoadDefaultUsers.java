package com.example.web.util;

import com.example.web.model.Role;
import com.example.web.model.User;
import com.example.web.repository.RoleRepository;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LoadDefaultUsers {
    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public LoadDefaultUsers(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @PostConstruct
    public void initTestUsers() {
        Role userTest = new Role("ROLE_USER");
        Role adminTest = new Role("ROLE_ADMIN");
        roleRepository.save(userTest);
        roleRepository.save(adminTest);
        Set<Role> userTestSet = Stream.of(userTest).collect(Collectors.toSet());
        Set<Role> adminTestSet = Stream.of(adminTest).collect(Collectors.toSet());

        User user = new User("user", "user", 22, "user", "user",userTestSet);
        User admin = new User("admin", "admin", 33, "admin", "admin", adminTestSet);
        userService.add(user);
        userService.add(admin);
    }
}