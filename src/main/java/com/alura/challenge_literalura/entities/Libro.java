package com.alura.challenge_literalura.entities;

import com.alura.challenge_literalura.model.Autor;
import com.alura.challenge_literalura.model.DatosLibro;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String categoria;
    private String idioma;
    private String portada;
    private Integer numeroDescargas;


    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autores().get(0);
        this.categoria = datosLibro.categorias().get(0);
        this.idioma = datosLibro.idioma().get(0);
        this.portada = datosLibro.formatos().uriPortada();
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("\n==================== LIBRO ====================\n");
        sb.append("Titulo = ").append(titulo).append('\n');
        sb.append("Autor = ").append(autor.getNombre()).append('\n');
        sb.append("Idioma = ").append(idioma).append('\n');
        sb.append("Portada = ").append(portada).append('\n');
        sb.append("Número Descargas = ").append(numeroDescargas).append("\n");
        return sb.toString();
    }
}
