package com.ledshra.Challenge_Foro_Hub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank(message = "Utilice su email")
        @Email(message = "Email inválido.")
        String email,
        @NotBlank(message = "De 10 y 15 caracteres.")
        String password
) {
}
