package com.example.menu;

/**
 * Una pregunta que espera una respuesta de tipo entero.
 */
public class PreguntaEntero extends PreguntaSimple {

    private int respuesta;

    public PreguntaEntero(String textoPregunta) {
        super(textoPregunta);
    }

    @Override
    protected void esperarRespuesta() {
        while (true) {
            try {
                respuesta = Integer.parseInt(scanner.nextLine());
                if(!esRespuestaValida()) {
                    System.out.println("Por favor, introduce un número entero válido.");
                } else {
                    break; // Salir del bucle si la respuesta es válida
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número entero válido.");
            }
        }
    }

    public int getRespuesta() {
        return respuesta;
    }
    
}
