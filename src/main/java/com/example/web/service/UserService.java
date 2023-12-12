package com.example.web.service;

import com.example.web.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String name);

    User showUser(Long id);

    void deleteUserById(Long id);

    List<User> getAllUsers();

    void updateUser(User user);
    void saveUser(User user);

}
