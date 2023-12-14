package com.example.web.service;



import com.example.web.model.Role;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Collection<Role> allRoles();
    Optional<Role> findByName(String name);
}
