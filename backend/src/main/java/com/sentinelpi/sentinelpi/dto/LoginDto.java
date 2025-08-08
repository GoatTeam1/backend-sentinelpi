package com.sentinelpi.sentinelpi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "El usuario es requerido")
    private String username;

    @NotBlank(message = "La contraseña requerida")
    private String password;

}
