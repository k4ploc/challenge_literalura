# LiterAlura

LiterAlura es una aplicación de consola que consume la API de [GutenDex](https://gutendex.com/) para buscar datos de
libros. La aplicación permite realizar búsquedas y consultas sobre los libros y autores registrados en la base de datos.

## Funcionalidades

El menú de la aplicación es el siguiente:

1. **Buscar libro por título**: Permite buscar un libro por su título en la API y guardarlo en la base de datos.
2. **Listar libros registrados**: Muestra una lista de todos los libros registrados en la base de datos.
3. **Listar autores registrados**: Muestra una lista de todos los autores registrados en la base de datos.
4. **Listar autores vivos de un determinado año**: Muestra una lista de autores que estaban vivos en un año específico.
5. **Listar libros por idioma**: Muestra una lista de libros registrados filtrados por idioma.

0. **Salir**: Cierra la aplicación.

## Requisitos

- Java 17 o superior
- Maven
- Base de datos configurada (PostgreSQL)

## Uso

Al iniciar la aplicación, verás el menú con las opciones disponibles. Selecciona una opción ingresando el número
correspondiente y sigue las instrucciones en pantalla.

### Buscar libro por título

1. Selecciona la opción `1 - Buscar libro por título`.
2. Ingresa el título del libro que deseas buscar.
3. La aplicación consultará la API de GutenDex y guardará el libro en la base de datos si aún no existe.

### Listar libros registrados

1. Selecciona la opción `2 - Listar libros registrados`.
2. La aplicación mostrará una lista de todos los libros registrados en la base de datos.

### Listar autores registrados

1. Selecciona la opción `3 - Listar autores registrados`.
2. La aplicación mostrará una lista de todos los autores registrados en la base de datos.

### Listar autores vivos de un determinado año

1. Selecciona la opción `4 - Listar autores vivos de un determinado año`.
2. Ingresa el año que deseas consultar.
3. La aplicación mostrará una lista de autores que estaban vivos en ese año.

### Listar libros por idioma

1. Selecciona la opción `5 - Listar libros por idioma`.
2. Ingresa el código del idioma (por ejemplo, `en` para inglés).
3. La aplicación mostrará una lista de libros registrados en ese idioma.

## Tecnologías utilizadas

- Java
- Spring Boot
- JPA/Hibernate
- Maven
- PostgreSQL (o cualquier otra base de datos compatible con JPA)
- API de [GutenDex](https://gutendex.com/)

## Contribuciones

¡Las contribuciones son bienvenidas! Si deseas contribuir, por favor abre un issue o envía un pull request.

## Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).
