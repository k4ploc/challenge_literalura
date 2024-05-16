package com.alura.challenge_literalura.model;

import com.alura.challenge_literalura.entities.Libro;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonAlias("name")
    private String nombre;
    @JsonAlias("birth_year")
    private Integer anioNacimiento;
    @JsonAlias("death_year")
    private Integer anioMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> libros;


    public Autor() {
    }

    public Autor(String nombre, Integer anioNacimiento, Integer anioMuerte) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
        this.anioMuerte = anioMuerte;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(Integer anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibro(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("nombre='").append(nombre).append('\'');
        sb.append(", anioNacimiento=").append(anioNacimiento);
        sb.append(", anioMuerte=").append(anioMuerte);
        return sb.toString();
    }


}
