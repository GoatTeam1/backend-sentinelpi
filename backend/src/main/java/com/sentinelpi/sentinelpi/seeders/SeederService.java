package com.sentinelpi.sentinelpi.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeederService {

    @Autowired private AttackSeeder attackSeeder;
    @Autowired private IpInfoSeeder ipInfoSeeder;
    @Autowired private LoginAttempSeeder loginAttempSeeder;

    public int seed(String collection, int cantidad) {
        return switch (collection) {
            case "attacks" -> attackSeeder.seed(cantidad);
            case "ip-info" -> ipInfoSeeder.seed(cantidad);
            case "login-attempts" -> loginAttempSeeder.seed(cantidad);
            default -> throw new IllegalArgumentException("Colección inválida: " + collection);
        };
    }
}
