package com.sentinelpi.sentinelpi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Formato de email invalido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

}
