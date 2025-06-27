package com.sentinelpi.sentinelpi.dto;

import com.sentinelpi.sentinelpi.models.Geolocation;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AttackDto {
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
