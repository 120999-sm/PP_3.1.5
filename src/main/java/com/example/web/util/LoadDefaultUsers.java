package com.example.web.util;

import com.example.web.model.Role;
import com.example.web.model.User;
import com.example.web.repository.RoleRepository;
import com.example.web.service.RoleService;
import com.example.web.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadDefaultUsers {
    private final RoleService roleService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public LoadDefaultUsers(RoleService roleService, UserService userService, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void createUsersWithRoles() {
        if (roleService.allRoles().isEmpty()) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            roleRepository.save(roleAdmin);
            List<Role> role_ad = new ArrayList<>();
            role_ad.add(roleAdmin);
            User admin = new User();
            admin.setAge(23);
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setUserName("admin");
            admin.setPassword("admin");
            admin.setRoles(role_ad);
            userService.add(admin); // Login: user; Password: user

            Role roleUser = new Role("ROLE_USER");
            roleRepository.save(roleUser);
            List<Role> role_us = new ArrayList<>();
            role_us.add(roleUser);
            User user = new User();
            user.setAge(32);
            user.setFirstName("user");
            user.setLastName("user");
            user.setUserName("user");
            user.setPassword("user");
            user.setRoles(role_us);
            userService.add(user); // Login: user; Password: user

        }
    }
}