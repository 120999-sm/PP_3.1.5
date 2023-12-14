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
   public List<User> allUsers() {
      return userRepository.allUsers();
   }

   @Transactional
   @Override
   public void add(User user) {
      user.setRoles(user.getRoles().stream()
              .map(r -> roleService.findByName(r.getName()))
              .filter(Optional::isPresent)
              .map(Optional::get)
              .collect(Collectors.toList()));
      userRepository.add(user);
   }

   @Transactional
   @Override
   public void remove(long id) {
      userRepository.remove(userRepository.getById(id));
   }

   @Transactional
   @Override
   public void edit(User user) {
      user.setRoles(user.getRoles().stream()
              .map(r -> roleService.findByName(r.getName()))
              .filter(Optional::isPresent)
              .map(Optional::get)
              .collect(Collectors.toList()));
      userRepository.edit(user);
   }

   @Override
   public User getById(long id) {
      return userRepository.getById(id);
   }

   @Override
   public User findByUsername(String userName) {
      return userRepository.findByUsername(userName);
   }

   @Override
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