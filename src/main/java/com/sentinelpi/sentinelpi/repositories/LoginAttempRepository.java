package com.sentinelpi.sentinelpi.repositories;

import com.sentinelpi.sentinelpi.models.LoginAttemp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginAttempRepository extends MongoRepository<LoginAttemp, String> {
}
