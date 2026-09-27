package com.gfg.showtime.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gfg.showtime.repository.UserRepository;

/*
 * How Spring Security finds a user at login. With this bean and a PasswordEncoder bean, Spring's
 * own DaoAuthenticationProvider does the rest: load the user, compare the password with the hash,
 * collect the authorities. (The course wrote that provider by hand; it isn't needed.)
 */
@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // The contract: throw when the user is missing, never return null
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }
}
