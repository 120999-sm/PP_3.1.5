package com.example.web.service;

import com.example.web.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleForName(String name);
    Role getRoleById(Long id);
}
