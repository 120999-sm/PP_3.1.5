package com.example.web.repository;


import com.example.web.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getUsersList();
    User getUser(Long id);
    void addUser(User user);
    void deleteUser(Long id);
    void editUser(User user);
    User findByUsername(String username);
}

