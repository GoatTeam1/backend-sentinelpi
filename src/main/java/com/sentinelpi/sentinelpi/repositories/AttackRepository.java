package com.sentinelpi.sentinelpi.repositories;

import com.sentinelpi.sentinelpi.models.Attack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttackRepository extends MongoRepository<Attack, String> {
}
