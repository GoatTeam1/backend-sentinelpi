package com.sentinelpi.sentinelpi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "attacks")
public class Attack {

    @Id
    private String id;

    private String ip;

    private int port;

    private String protocol;

    private String os;

    private Date timestamp;

    private String payload;

    private List<String> tools;

    private String behavior;

    private Geolocation geolocation;

}