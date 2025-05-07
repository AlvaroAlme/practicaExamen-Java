package com.example.menu;

public class PreguntaDNI extends PreguntaTexto {
    
    private String respuesta;

    public PreguntaDNI(String textoPregunta){
        super(textoPregunta);
    }

    

    @Override
    protected boolean esRespuestaValida() {
        return respuesta.matches("^[0-9]{8}[A-Z]$");
    }

}
