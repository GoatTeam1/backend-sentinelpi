package com.sentinelpi.sentinelpi.services;

import com.sentinelpi.sentinelpi.models.User;
import com.sentinelpi.sentinelpi.repositories.UserRepository;
import com.sentinelpi.sentinelpi.utils.JwtUtil;
import com.sentinelpi.sentinelpi.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordUtil passwordUtil;
    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password){
        User user = userRepository.findByUsername(username).orElse(null);
        if(user == null){
            return null;
        }
        boolean isPasswordMatch = passwordUtil.verifyPassword(password, user.getPassword());
        if(isPasswordMatch){
            return null;
        }
        return jwtUtil.generateToken(user.getUsername(), user.getId());
    }
}
