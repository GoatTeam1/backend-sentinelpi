package com.sentinelpi.sentinelpi.services;
import com.sentinelpi.sentinelpi.dto.UpdateUserDto;
import com.sentinelpi.sentinelpi.dto.UserDto;
import com.sentinelpi.sentinelpi.exceptions.EmailAlreadyExistsException;
import com.sentinelpi.sentinelpi.models.User;
import com.sentinelpi.sentinelpi.repositories.UserRepository;
import com.sentinelpi.sentinelpi.utils.PasswordUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public User create(UserDto dto) {
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new EmailAlreadyExistsException(dto.getEmail());
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(PasswordUtil.hashPassword(dto.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> update(String id, @Valid UpdateUserDto dto) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());

            if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
                user.setPassword(PasswordUtil.hashPassword(dto.getPassword()));
            }

            return userRepository.save(user);
        });
    }


    public boolean delete(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
