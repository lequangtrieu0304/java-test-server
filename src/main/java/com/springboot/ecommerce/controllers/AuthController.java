package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.config.JwtProvider;
import com.springboot.ecommerce.dtos.LoginDTO;
import com.springboot.ecommerce.exceptions.UserException;
import com.springboot.ecommerce.models.User;
import com.springboot.ecommerce.response.AuthResponse;
import com.springboot.ecommerce.repositories.UserRepository;
import com.springboot.ecommerce.services.impl.CustomUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserServiceImpl customUserService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String lastName = user.getLastName();
        String firstName = user.getFirstName();

        User isEmailExist = userRepository.findByEmail(email);
        if (isEmailExist != null) {
            throw new UserException("Email already exists");
        }

        User createdUser = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .lastName(lastName)
                .firstName(firstName)
                .build();
        User savedUser = userRepository.save(createdUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtProvider.generateToken(auth);
        AuthResponse authResponse = new AuthResponse(token, "Signup Successful");
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginDTO loginDTO) throws UserException {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = AuthResponse
                .builder()
                .jwt(token)
                .message("Login successfully!")
                .build();

        return ResponseEntity.ok(authResponse);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }
}