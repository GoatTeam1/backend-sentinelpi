package com.sentinelpi.sentinelpi.services;

import com.sentinelpi.sentinelpi.dto.LoginAttempDto;
import com.sentinelpi.sentinelpi.models.LoginAttemp;
import com.sentinelpi.sentinelpi.repositories.LoginAttempRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LoginAttempService {
    @Autowired
    private LoginAttempRepository loginAttempRepository;

    public List<LoginAttemp> findAll(){
        return loginAttempRepository.findAll();
    }

    public Optional<LoginAttemp> findById(String id){
        return loginAttempRepository.findById(id);
    }

    public LoginAttemp Create(LoginAttempDto dto){
        LoginAttemp loginAttemp = new LoginAttemp();
        loginAttemp.setPassword(dto.getPassword());
        loginAttemp.setUsername(dto.getUsername());
        return loginAttempRepository.save(loginAttemp);
    }
    /*
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
    *
    *
    * */
}

