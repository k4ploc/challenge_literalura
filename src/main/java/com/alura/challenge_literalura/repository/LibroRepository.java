package com.alura.challenge_literalura.repository;

import com.alura.challenge_literalura.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

    @Query("SELECT l FROM Libro l WHERE l.titulo ILIKE %:titulo%")
    Optional<Libro> findByTitulo(@Param("titulo") String titulo);

}
