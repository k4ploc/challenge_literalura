package com.alura.challenge_literalura.util;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
