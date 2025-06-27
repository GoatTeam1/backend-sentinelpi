package com.sentinelpi.sentinelpi.repositories;

import com.sentinelpi.sentinelpi.models.IpInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IpInfoRepository extends MongoRepository<IpInfo,  String> {

}
