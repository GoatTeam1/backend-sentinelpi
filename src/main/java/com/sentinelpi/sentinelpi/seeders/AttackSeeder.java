package com.sentinelpi.sentinelpi.seeders;

import com.github.javafaker.Faker;
import com.sentinelpi.sentinelpi.models.*;
import com.sentinelpi.sentinelpi.repositories.AttackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AttackSeeder {

    @Autowired
    private AttackRepository attackRepository;

    private final Faker faker = new Faker();

    public int seed(int cantidad) {
        List<Attack> list = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Attack attack = new Attack();
            attack.setIp(faker.internet().ipV4Address());
            attack.setPort(faker.number().numberBetween(100, 9999));
            attack.setProtocol(faker.options().option("TCP", "UDP", "ICMP"));
            attack.setTimestamp(faker.date().past(365, java.util.concurrent.TimeUnit.DAYS));
            attack.setPayload(faker.lorem().sentence());
            attack.setTools(Arrays.asList(faker.hacker().verb(), faker.hacker().noun()));
            attack.setBehavior(faker.options().option("SCAN", "DOS", "BRUTE_FORCE", "SPYWARE"));

            Geolocation geo = new Geolocation();
            geo.setCountry(faker.country().name());
            geo.setCity(faker.address().city());
            attack.setGeolocation(geo);

            list.add(attack);
        }
        attackRepository.saveAll(list);
        return list.size();
    }
}
