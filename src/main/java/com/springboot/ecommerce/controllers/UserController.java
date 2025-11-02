package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.exceptions.UserException;
import com.springboot.ecommerce.models.User;
import com.springboot.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> getMe() throws UserException {
        return ResponseEntity.ok(userService.getMe());
    }
}
