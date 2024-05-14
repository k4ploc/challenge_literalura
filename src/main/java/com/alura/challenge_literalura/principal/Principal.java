package com.alura.challenge_literalura.principal;

import com.alura.challenge_literalura.entities.Libro;
import com.alura.challenge_literalura.model.Autor;
import com.alura.challenge_literalura.model.DatosLibro;
import com.alura.challenge_literalura.model.ResponseLibro;
import com.alura.challenge_literalura.repository.LibroRepository;
import com.alura.challenge_literalura.service.ConsumoAPI;
import com.alura.challenge_literalura.util.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String API_KEY = "TU-APIKEY-OMDB";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> libros;

    private final LibroRepository libroRepository;

    public Principal(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                                        
                    Bienvenido a LiterAlura!
                                        
                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos de un determinado año
                    5 - Listar libros por idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWebApi();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...\nGracias por usar LiterAlura!\n");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void mostrarLibrosRegistrados() {
        libros = libroRepository.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el titulo del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        System.out.println(json);
        return null; //conversor.obtenerDatos(json, DatosLibro.class);
    }

    private DatosLibro getDatosLibros() {
        System.out.println("Escribe el titulo del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        System.out.println(json);
        return conversor.obtenerDatos(json, ResponseLibro.class).results().get(0);
    }

    private void buscarLibroWebApi() {
        DatosLibro datos = getDatosLibros();
        Libro libro = new Libro(datos);
        for (Autor autor : libro.getAutores()) {
            autor.setLibro(libro);
        }
        libroRepository.save(libro);
        //datosSeries.add(datos);
        System.out.println("datos" + datos);
    }
}
