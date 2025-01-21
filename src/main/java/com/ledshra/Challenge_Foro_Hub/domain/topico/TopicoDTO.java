package com.ledshra.Challenge_Foro_Hub.domain.topico;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TopicoDTO(
        @NotNull(message = "Ya existe título.")
        String title,
        @NotNull(message = "Mensaje extenso.")
        String message,
        @NotNull(message = "Seleccione Estado ´ACTIVO´ o ´INACTIVO´")
        Status status,
        @NotNull(message = "Utilice su ID de autor")
        Long usuario_Id,
        @NotNull(message = "Utiliza el curso para tu publicación.")
        String curso,
        LocalDateTime date
) {
}