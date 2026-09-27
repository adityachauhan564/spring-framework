package com.gfg.showtime.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gfg.showtime.domain.User;
import com.gfg.showtime.enums.Role;
import com.gfg.showtime.exception.ConflictException;
import com.gfg.showtime.exception.NotFoundException;
import com.gfg.showtime.repository.UserRepository;
import com.gfg.showtime.resource.SignupRequest;
import com.gfg.showtime.resource.UserResource;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResource signup(SignupRequest request) {
        return User.toResource(create(request, Role.USER));
    }

    // Also used by DataSeeder to create the admin account
    @Transactional
    public User create(SignupRequest request, Role role) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ConflictException("Email already registered: " + request.email());
        }
        if (userRepository.existsByMobile(request.mobile())) {
            throw new ConflictException("Mobile already registered: " + request.mobile());
        }
        return userRepository.save(User.builder()
                .name(request.name())
                .email(request.email())
                .mobile(request.mobile())
                .password(passwordEncoder.encode(request.password()))   // store only the hash
                .role(role)
                .build());
    }

    @Transactional(readOnly = true)
    public UserResource getUser(long id) {
        return userRepository.findById(id).map(User::toResource)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));
    }

    @Transactional(readOnly = true)
    public UserResource getByEmail(String email) {
        return userRepository.findByEmail(email).map(User::toResource)
                .orElseThrow(() -> new NotFoundException("User not found: " + email));
    }
}
