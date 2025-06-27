package com.sentinelpi.sentinelpi.dto;

import com.sentinelpi.sentinelpi.models.Geolocation;
import lombok.Data;

import java.util.Date;

@Data
public class IpInfoDto {
    private String ip;

    private Date lastActivity;

    private Geolocation geolocation;
}
