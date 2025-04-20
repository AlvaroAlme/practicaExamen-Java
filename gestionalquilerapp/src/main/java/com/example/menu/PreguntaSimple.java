package com.example.menu;

/**
 * Clase abstracta que representa una pregunta con una única respuesta.
 */
public abstract class PreguntaSimple extends Pregunta {

    private final String textoPregunta;

    protected PreguntaSimple(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    @Override
    protected void imprimir() {
        System.out.println(textoPregunta);
    }

    /**
     * Comprueba la respuesta del usuario. Este método existe para ser sobreescrito
     * por las subclases que necesiten comprobar la respuesta del usuario.
     * @return True si la respuesta es correcta, false en caso contrario.
     */
    protected boolean esRespuestaValida() {
        return true;
    }
    
}
