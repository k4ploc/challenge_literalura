package com.alura.challenge_literalura.repository;

import com.alura.challenge_literalura.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    
}
