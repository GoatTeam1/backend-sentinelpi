package com.sentinelpi.sentinelpi.repositories;

import com.sentinelpi.sentinelpi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
}
