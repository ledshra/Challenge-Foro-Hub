package com.ledshra.Challenge_Foro_Hub.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record ActualizacionUsuarioDTO(
        @NotNull Long id,
        String name,
        String email
) {
}