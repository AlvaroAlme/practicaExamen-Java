package com.example.menu;

/**
 * Una pregunta que espera una respuesta de tipo opción.
 * 
 * Esta clase extiende la clase Pregunta y permite al usuario seleccionar una opción de un conjunto predefinido.
 * 
 * @see Pregunta
 */
public class PreguntaOpciones extends Pregunta {

    private final String[] opciones;
    private int respuesta;

    public PreguntaOpciones(String[] opciones) {
        super();
        this.opciones = opciones;
    }

    @Override
    protected void imprimir() {
        System.out.println("Seleccione una opción:");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
    }

    @Override
    protected void esperarRespuesta() {
        while (true) {
            respuesta = scanner.nextInt();
            if (respuesta < 1 || respuesta > opciones.length) {
                System.out.println("Opción no válida. Por favor introduzca un número entre el 1 y el " + opciones.length + " (ambos inclusive).");
            } else {
                break; // Salir del bucle si la respuesta es válida
            }
        }
    }

    public int getRespuesta() {
        return respuesta;
    }
    
}
