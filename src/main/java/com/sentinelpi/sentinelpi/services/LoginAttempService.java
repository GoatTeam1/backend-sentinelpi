package com.sentinelpi.sentinelpi.services;

import com.sentinelpi.sentinelpi.dto.LoginAttempDto;
import com.sentinelpi.sentinelpi.models.LoginAttemp;
import com.sentinelpi.sentinelpi.repositories.AttackRepository;
import com.sentinelpi.sentinelpi.repositories.LoginAttempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginAttempService {

    @Autowired
    private LoginAttempRepository loginAttempRepository;

    @Autowired
    private AttackRepository attackRepository;

    public List<LoginAttemp> findAll() {
        return loginAttempRepository.findAll();
    }

    public Optional<LoginAttemp> findById(String id) {
        return loginAttempRepository.findById(id);
    }

    public LoginAttemp create(LoginAttempDto dto) {
        if (dto.getAttackId() != null && !dto.getAttackId().isBlank()) {
            if (!attackRepository.existsById(dto.getAttackId())) {
                throw new IllegalArgumentException("Attack id no existe: " + dto.getAttackId());
            }
        }

        LoginAttemp loginAttemp = new LoginAttemp();
        loginAttemp.setUsername(dto.getUsername());
        loginAttemp.setPassword(dto.getPassword());
        loginAttemp.setTimestamp(dto.getTimestamp());
        loginAttemp.setAttackId(dto.getAttackId());

        return loginAttempRepository.save(loginAttemp);
    }

    public Optional<LoginAttemp> update(String id, LoginAttempDto dto) {
        return loginAttempRepository.findById(id).map(existing -> {
            if (dto.getAttackId() != null && !dto.getAttackId().isBlank()) {
                if (!attackRepository.existsById(dto.getAttackId())) {
                    throw new IllegalArgumentException("Attack id no existe: " + dto.getAttackId());
                }
                existing.setAttackId(dto.getAttackId());
            }

            existing.setUsername(dto.getUsername());
            existing.setPassword(dto.getPassword());
            existing.setTimestamp(dto.getTimestamp());

            return loginAttempRepository.save(existing);
        });
    }

    public boolean delete(String id) {
        if (loginAttempRepository.existsById(id)) {
            loginAttempRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
