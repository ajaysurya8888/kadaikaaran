package com.kadaikaaran.kadaikaaran.service;

import com.kadaikaaran.kadaikaaran.dao.Agent;
import com.kadaikaaran.kadaikaaran.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Agent> agent = userRepository.findByEmailId(userName);
        if (agent.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(agent.get().getRole()) // Ensure role is prefixed with "ROLE_"
        );

        return User.withUsername(agent.get().getEmailId())
                .password(agent.get().getPassword()) // Password should be hashed
                .roles(agent.get().getRole().replace("ROLE_", "")) // Convert ROLE_ADMIN to ADMIN
                .build();
    }
}
