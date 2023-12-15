package com.example.web.controller;

import com.example.web.model.User;
import com.example.web.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/authorizedUser")
    public ResponseEntity<?> getAuthorizedUser() {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
