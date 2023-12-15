package com.example.web.repository;

import com.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findById (Long id);

    User findByUsername(String username);

    User getById (Long id);



//    List<User> allUsers();
//
//    void add(User user);
//
//    void remove(User user);
//
//    void edit(User user);
//
//    User getById(long id);
//
//    User findByUsername(String username);
}
