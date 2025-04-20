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
        respuesta = scanner.nextLine();
    }

    public String getRespuesta() {
        return respuesta;
    }
    
}
