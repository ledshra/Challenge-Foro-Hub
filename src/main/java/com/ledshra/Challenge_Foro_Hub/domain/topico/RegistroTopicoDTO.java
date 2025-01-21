package com.ledshra.Challenge_Foro_Hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroTopicoDTO (
        @NotBlank(message = "Ingrese Título")
        String title,
        @NotBlank(message = "Ingrese Mensaje")
        String message,
        @NotBlank(message = "Ingrese Curso")
        String course,
        @NotNull(message = "Ingrese Author")
        Long author_id
) {

    public RegistroTopicoDTO(String title, String message, String course, Long author_id){
        this.title = title;
        this.message = message;
        this.course = course;
        this.author_id = author_id;
    }
}