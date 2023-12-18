package com.example.web.repository;

import com.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

//    User findById(Long id);

    @Query("Select u from User u left join fetch u.roles where u.username=:username")
    User findByUsername(String username);

    User getById (Long id);

}
