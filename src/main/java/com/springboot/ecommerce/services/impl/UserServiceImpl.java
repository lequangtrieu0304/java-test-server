package com.springboot.ecommerce.services.impl;

import com.springboot.ecommerce.exceptions.UserException;
import com.springboot.ecommerce.models.User;
import com.springboot.ecommerce.repositories.UserRepository;
import com.springboot.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User getMe() throws UserException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user;
        try {
            user = userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return user;
    }
}
