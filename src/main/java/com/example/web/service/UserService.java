package com.example.web.service;



import com.example.web.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsersList();
    User getUser(Long id);
    void addUser(User user);
    void deleteUser(Long id);
    void editUser(User user);
    User findByUsername(String username);
}
