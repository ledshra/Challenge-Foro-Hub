package com.ledshra.Challenge_Foro_Hub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegistroUsuarioDTO(
        Long id, @NotBlank
        String name,
        @NotBlank (message = "Utilice su email")
        @Email
        String username,
        @NotBlank
        String email,
        @NotBlank(message = "De 6 y 10 caracteres.") @Pattern(regexp = "\\d{6,10}")
        String password

) {
        public Long getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public String getEmail() {
                return email;
        }
}