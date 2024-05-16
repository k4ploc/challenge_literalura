package com.alura.challenge_literalura.repository;

import com.alura.challenge_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Optional<Autor> findByNombre(String nombre);

    List<Autor> findByAnioNacimientoLessThanEqualOrAnioMuerteLessThan(Integer anioNacimiento, Integer anioMuerte);

    @Query(nativeQuery = true, value = "SELECT libros.titulo FROM libros JOIN autores ON libros.autor_id = autores.id WHERE autores.nombre = :nombre")
    List<String> findLibrosByAutorNombre(@Param("nombre") String nombre);


}
