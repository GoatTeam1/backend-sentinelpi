package com.sentinelpi.sentinelpi.seeders;

import com.github.javafaker.Faker;
import com.sentinelpi.sentinelpi.models.Geolocation;
import com.sentinelpi.sentinelpi.models.IpInfo;
import com.sentinelpi.sentinelpi.repositories.IpInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class IpInfoSeeder {

    @Autowired
    private IpInfoRepository ipInfoRepository;

    private final Faker faker = new Faker();

    public int seed(int cantidad) {
        List<IpInfo> list = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            IpInfo info = new IpInfo();
            info.setIp(faker.internet().ipV4Address());
            info.setLastActivity(faker.date().past(30, java.util.concurrent.TimeUnit.DAYS));
            Geolocation geo = new Geolocation();
            geo.setCountry(faker.country().name());
            geo.setCity(faker.address().city());
            info.setGeolocation(geo);
            list.add(info);
        }
        ipInfoRepository.saveAll(list);
        return list.size();
    }
}
