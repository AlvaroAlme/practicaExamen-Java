package com.example.menu;

/**
 * Una pregunta que espera una respuesta de tipo entero positivo.
 * Ejemplo para el uso de las validaciones (esRespuestaValida).
 */
public class PreguntaEnteroPositivo extends PreguntaEntero {

    public PreguntaEnteroPositivo(String textoPregunta) {
        super(textoPregunta);
    }
    
    @Override
    protected boolean esRespuestaValida() {
        return getRespuesta() > 0;
    }

}
