package com.sentinelpi.sentinelpi.services;

import com.sentinelpi.sentinelpi.models.User;
import com.sentinelpi.sentinelpi.repositories.UserRepository;
import com.sentinelpi.sentinelpi.utils.JwtUtil;
import com.sentinelpi.sentinelpi.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
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
            System.out.println("[AuthService] Usuario no encontrado");
            return null;
        }

        boolean isPasswordMatch = passwordUtil.verifyPassword(password, user.getPassword());
        System.out.println("[AuthService] Coincidencia de contraseña: " + isPasswordMatch);

        if (!isPasswordMatch) {
            return null;
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getId());
        System.out.println("[AuthService] Token generado: " + token);

        return token;
    }


}
