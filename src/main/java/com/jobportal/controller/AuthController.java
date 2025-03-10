package com.jobportal.controller;

import com.jobportal.model.entity.User;
import com.jobportal.model.enums.Role;
import com.jobportal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String roleStr = request.get("role");

        if (username == null || password == null || roleStr == null) {
            return "Error: Provide 'username', 'password', and 'role'!";
        }

        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return "Error: Username already exists!";
        }


        Role role;
        try {
            role = Role.valueOf(roleStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Error: Invalid role! Use 'ADMIN', 'EMPLOYER', or 'JOB_SEEKER'.";
        }


        User newUser = new User(username, passwordEncoder.encode(password), role);
        userRepository.save(newUser);

        return "Success: User registered successfully!";
    }


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Job Portal Authentication API!";
    }
}
