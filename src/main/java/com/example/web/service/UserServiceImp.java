package com.example.web.service;

import com.example.web.model.Role;
import com.example.web.model.User;
import com.example.web.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.annotations.QueryHints.READ_ONLY;

@Service
@Transactional
public class UserServiceImp implements UserService {

   private final UserRepository userRepository;
   private final RoleService roleService;


   @Autowired
   public UserServiceImp(UserRepository userRepository, RoleService roleService) {
      this.userRepository = userRepository;
      this.roleService = roleService;
   }

   @Override
   @Transactional(READ_ONLY)
   public List<User> allUsers() {
      return userRepository.findAll();
   }


   @Override
   public void add(User user) {
      user.setRoles(user.getRoles().stream()
              .map(r -> roleService.findByName(r.getName()))
              .filter(Optional::isPresent)
              .map(Optional::get)
              .collect(Collectors.toList()));
      userRepository.save(user);
   }

   @Override
   public void remove(long id) {
      userRepository.delete(userRepository.getById(id));
   }

   @Override
   public void edit(User user) {
      user.setRoles(user.getRoles().stream()
              .map(r -> roleService.findByName(r.getName()))
              .filter(Optional::isPresent)
              .map(Optional::get)
              .collect(Collectors.toList()));
      userRepository.save(user);
   }

   @Override
   @Transactional(READ_ONLY)
   public User getById(long id) {
      Optional<User> user = userRepository.findById(id);
      return user.orElse(null);
   }

   @Override
   @Transactional(READ_ONLY)
   public User findByUsername(String userName) {
      return userRepository.findByUsername(userName);
   }

   @Override
   @Transactional(READ_ONLY)
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByUsername(username);
      if (user == null) {
         throw new UsernameNotFoundException("User not found");
      }
      return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
   }

   private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
      return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
   }
}