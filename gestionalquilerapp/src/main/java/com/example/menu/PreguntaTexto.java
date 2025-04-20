package com.example.menu;

/**
 * Una pregunta que espera una respuesta de texto.
 */
public class PreguntaTexto extends PreguntaSimple {

    private String respuesta;

    public PreguntaTexto(String textoPregunta) {
        super(textoPregunta);
    }

    @Override
    protected void esperarRespuesta() {
        while(true) {
            respuesta = scanner.nextLine();
            if(!esRespuestaValida()) {
                System.out.println("Por favor, introduce una respuesta válida.");
            } else {
                break; // Salir del bucle si la respuesta es válida
            }
        }
        
    }

    public String getRespuesta() {
        return respuesta;
    }
    
}
