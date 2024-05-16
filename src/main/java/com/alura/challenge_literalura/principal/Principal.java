package com.alura.challenge_literalura.principal;

import com.alura.challenge_literalura.entities.Libro;
import com.alura.challenge_literalura.model.Autor;
import com.alura.challenge_literalura.model.DatosLibro;
import com.alura.challenge_literalura.model.ResponseLibro;
import com.alura.challenge_literalura.repository.AutorRepository;
import com.alura.challenge_literalura.repository.LibroRepository;
import com.alura.challenge_literalura.service.ConsumoAPI;
import com.alura.challenge_literalura.util.ConvierteDatos;

import java.util.*;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String API_KEY = "TU-APIKEY-OMDB";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libro> libros;
    private List<Autor> autores;

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
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
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresPorAnio();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...\nGracias por usar LiterAlura!\n");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void mostrarLibrosPorIdioma() {
        mostrarMenuIdiomas();
        String idiomaBuscado = teclado.nextLine().toLowerCase();
        mostrarLibrosRegistradosPorIdioma(idiomaBuscado);
    }

    private boolean validarIdioma(String idioma) {
        String[] listaIdiomas = {"es", "en", "fr", "pt"};
        return Arrays.asList(listaIdiomas).contains(idioma);

    }

    private void mostrarMenuIdiomas() {
        System.out.println("Seleccione el idioma de la lista: ");
        System.out.println("""
                es - Español
                en - Inglés
                fr - Francés
                pt - Portugués                
                """);
    }

    private void mostrarAutoresPorAnio() {
        System.out.println("Escribe el año por el cual desea buscar:");
        var anioBuscado = teclado.nextInt();
        autores = autorRepository.findByAnioNacimientoLessThanEqualOrAnioMuerteLessThan(anioBuscado, anioBuscado);

        for (Autor autor : autores) {
            List<String> libros = autorRepository.findLibrosByAutorNombre(autor.getNombre());
            System.out.println("\n==================== AUTOR ====================\n");
            System.out.println("Autor : " + autor.getNombre());
            System.out.println("Fecha nacimiento : " + autor.getAnioNacimiento());
            System.out.println("Fecha fallecimiento : " + autor.getAnioMuerte());
            System.out.println("Libros : " + libros);
        }
    }

    private void mostrarAutoresRegistrados() {
        autores = autorRepository.findAll();
        List<String> libros = List.of();
        for (Autor autor : autores) {
            libros = autorRepository.findLibrosByAutorNombre(autor.getNombre());
            System.out.println("\n==================== AUTOR ====================\n");
            System.out.println("Autor : " + autor.getNombre());
            System.out.println("Fecha nacimiento : " + autor.getAnioNacimiento());
            System.out.println("Fecha fallecimiento : " + autor.getAnioMuerte());
            System.out.println("Libros : " + libros);
        }


    }

    private void mostrarLibrosRegistrados() {
        libros = libroRepository.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    private void mostrarLibrosRegistradosPorIdioma(String idioma) {


        if (!validarIdioma(idioma)) {
            System.out.println("Opcion invalida");
            return;
        }

        libros = libroRepository.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .filter(l -> l.getIdioma().equals(idioma))
                .forEach(System.out::println);
    }


    private DatosLibro getDatosLibros() {
        System.out.println("Escribe el titulo del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        Optional<Libro> libroExistente = libroRepository.findByTitulo(tituloLibro);
        // Si el libro ya existe, muestra un mensaje y termina el método
        if (libroExistente.isPresent()) {
            System.out.println("\nEl libro '" + tituloLibro + "' ya ha sido registrado anteriormente.");
            return null;
        } else {
            var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
            System.out.println(json);
            return conversor.obtenerDatos(json, ResponseLibro.class).results().get(0);
        }

    }

    private void buscarLibroWebApi() {

        DatosLibro datos = getDatosLibros();
        if (datos == null) {
            return;
        }
        Libro libro = new Libro(datos);

        Autor autor = libro.getAutor();

        Optional<Autor> autorExistente = autorRepository.findByNombre(autor.getNombre());

        if (autorExistente.isEmpty()) {
            autorRepository.save(autor);
        } else {
            libro.setAutor(autorExistente.get());
        }

        libroRepository.save(libro);
        System.out.println("datos" + datos);
    }


}
