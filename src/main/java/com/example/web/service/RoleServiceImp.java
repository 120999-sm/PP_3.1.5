package com.example.web.service;

import com.example.web.model.Role;
import com.example.web.repository.RoleRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoleServiceImp implements RoleService{
    private RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
