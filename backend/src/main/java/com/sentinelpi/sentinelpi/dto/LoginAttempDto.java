package com.sentinelpi.sentinelpi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LoginAttempDto {
    private String id;

    private String attackId;

    private String username;

    private String password;

    private Date timestamp;

}
