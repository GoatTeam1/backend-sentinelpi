package com.sentinelpi.sentinelpi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "login_attemps")
public class LoginAttemp {
    @Id
    private String id;

    private String attackId;

    private String username;

    private String password;

    private Date timestamp;

}
