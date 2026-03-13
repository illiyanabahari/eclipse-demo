package com.abc.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abc.entity.AppUser;
import com.abc.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository repo, BCryptPasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public AppUser authenticate(String username, String rawPassword) {

        if (username == null || rawPassword == null) return null;

        String u = username.trim();
        if (u.isEmpty()) return null;

        AppUser user = repo.findActiveByUsername(u);
        if (user == null) return null;

        boolean ok = passwordEncoder.matches(rawPassword, user.getPassword());
        return ok ? user : null;
    }
}
