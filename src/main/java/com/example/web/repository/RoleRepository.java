package com.example.web.repository;

import com.example.web.model.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> getAllRoles();
     Role getRoleForName(String name);
     Role getRoleById(Long id);

}
