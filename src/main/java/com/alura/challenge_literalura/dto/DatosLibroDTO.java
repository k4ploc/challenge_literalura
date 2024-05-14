package com.alura.challenge_literalura.dto;

import com.alura.challenge_literalura.model.Autor;

public record DatosLibroDTO(
        String titulo,
        Autor autor,
        String categoria,
        String idioma,
        String portada,
        Integer numeroDescargas
) {
}
