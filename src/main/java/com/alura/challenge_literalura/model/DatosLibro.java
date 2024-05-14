package com.alura.challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autor> autores,
        @JsonAlias("subjects") List<String> categorias,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("formats") Formats formatos,
        @JsonAlias("download_count") Integer numeroDescargas) {
}
