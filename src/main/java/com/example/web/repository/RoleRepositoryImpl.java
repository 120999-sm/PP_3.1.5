package com.example.web.repository;

import com.example.web.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }
    @Override
    public Role getRoleForName(String name) {
        return entityManager.createQuery("select r from Role r where r.role =: role", Role.class)
                    .setParameter("role", name).getSingleResult();
    }
    @Override
    public Role getRoleById(Long id) {
            return entityManager.find(Role.class, id);
        }
}

