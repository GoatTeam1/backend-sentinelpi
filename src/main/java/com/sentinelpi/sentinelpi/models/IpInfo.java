package com.sentinelpi.sentinelpi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "ip_info")
public class IpInfo {
    @Id
    private String id;

    private String ip;

    private Date lastActivity;

    private Geolocation geolocation;

}
