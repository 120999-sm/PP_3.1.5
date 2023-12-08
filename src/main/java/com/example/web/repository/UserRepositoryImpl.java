package com.example.web.repository;

import com.example.web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getUsersList() {
        return entityManager.createQuery("SELECT u FROM User u",User.class).getResultList();
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByUsername(String username) {
        Query findByUsernameQuery = entityManager.createQuery
                ("select u from User u left join fetch u.roles where u.userName=:username", User.class);
        findByUsernameQuery.setParameter("username", username);
        return (User) findByUsernameQuery.getSingleResult();
    }

}
