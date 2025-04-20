package com.example.menu;

import java.util.Scanner;

public abstract class Pregunta {

    protected final Scanner scanner;

    protected Pregunta() {
        scanner = new Scanner(System.in);
    }

    /**
     * Método que imprime la pregunta en la consola.
     */
    protected abstract void imprimir();
    /**
     * Método que espera la respuesta del usuario.
     */
    protected abstract void esperarRespuesta();

    /**
     * Método que imprime la pregunta y espera la respuesta del usuario.
     */
    public void preguntar() {
        this.imprimir();
        this.esperarRespuesta();
    }
    
}
