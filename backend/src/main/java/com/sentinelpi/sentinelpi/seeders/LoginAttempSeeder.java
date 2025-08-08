package com.sentinelpi.sentinelpi.seeders;

import com.github.javafaker.Faker;
import com.sentinelpi.sentinelpi.models.LoginAttemp;
import com.sentinelpi.sentinelpi.repositories.LoginAttempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAttempSeeder {

    @Autowired
    private LoginAttempRepository loginAttempRepository;

    private final Faker faker = new Faker();

    public int seed(int cantidad) {
        List<LoginAttemp> list = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            LoginAttemp attempt = new LoginAttemp();
            attempt.setUsername(faker.name().username());
            attempt.setPassword(faker.internet().password());
            attempt.setTimestamp(faker.date().past(15, TimeUnit.DAYS));
            attempt.setAttackId(null); // Si deseas, aquí puedes generar o seleccionar uno real
            list.add(attempt);
        }
        loginAttempRepository.saveAll(list);
        return list.size();
    }
}
