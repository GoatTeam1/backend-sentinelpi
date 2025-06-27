package com.sentinelpi.sentinelpi.services;

import com.sentinelpi.sentinelpi.dto.AttackDto;
import com.sentinelpi.sentinelpi.models.Attack;
import com.sentinelpi.sentinelpi.repositories.AttackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttackService {

    private final AttackRepository attackRepository;

    public AttackService(AttackRepository attackRepository) {
        this.attackRepository = attackRepository;
    }

    public List<Attack> findAll() {
        return attackRepository.findAll();
    }

    public Optional<Attack> findById(String id) {
        return attackRepository.findById(id);
    }

    public Attack create(AttackDto dto) {
        Attack attack = new Attack();
        attack.setIp(dto.getIp());
        attack.setPort(dto.getPort());
        attack.setProtocol(dto.getProtocol());
        attack.setTimestamp(dto.getTimestamp());
        attack.setPayload(dto.getPayload());
        attack.setTools(dto.getTools());
        attack.setBehavior(dto.getBehavior());
        attack.setGeolocation(dto.getGeolocation());

        return attackRepository.save(attack);
    }

    public Optional<Attack> update(String id, AttackDto dto) {
        return attackRepository.findById(id).map(existing -> {
            existing.setIp(dto.getIp());
            existing.setPort(dto.getPort());
            existing.setProtocol(dto.getProtocol());
            existing.setTimestamp(dto.getTimestamp());
            existing.setPayload(dto.getPayload());
            existing.setTools(dto.getTools());
            existing.setBehavior(dto.getBehavior());
            existing.setGeolocation(dto.getGeolocation());

            return attackRepository.save(existing);
        });
    }

    public boolean delete(String id) {
        if (attackRepository.existsById(id)) {
            attackRepository.deleteById(id);
            return true;}
        return false;
    }
}
