package com.example.web.repository;

import com.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


public interface UserRepository  {
    List<User> allUsers();

    void add(User user);

    void remove(User user);

    void edit(User user);

    User getById(long id);

    User findByUsername(String username);
}
