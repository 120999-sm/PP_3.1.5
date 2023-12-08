package com.example.web.controller;

import com.example.web.model.Role;
import com.example.web.model.User;
import com.example.web.service.RoleService;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/users")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("thisUser", user);
        model.addAttribute("users", userService.getUsersList());
        model.addAttribute("flag", user.getUserRolesForUI().contains("ADMIN"));
        return "mainMenu";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user,@RequestParam("checkRoles") String[] selectResult) {
        Set<Role> roles = new HashSet<>();
        for (String s : selectResult) {
            roles.add(roleService.getRoleForName("ROLE_" + s));
            user.setRoles(roles);
        }
        userService.editUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("User") User user, @RequestParam("checkRoles") String[] selectResult) {
        Set<Role> roles = new HashSet<>();
        for (String s : selectResult) {
            roles.add(roleService.getRoleForName("ROLE_" + s));
            user.setRoles(roles);
        }
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
