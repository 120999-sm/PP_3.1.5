package com.example.web.service;

import com.example.web.model.Role;
import com.example.web.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Set<Role> allRoles() {
        List<Role> list = roleRepository.findAll();
        return new HashSet<>(list);
    }


}
