package com.example.menu;

public class PreguntaDNI extends PreguntaTexto {
    
    private String respuesta;

    public PreguntaDNI(String textoPregunta){
        super(textoPregunta);
    }

    @Override
    protected void esperarRespuesta(){
        while(true){
            respuesta = scanner.nextLine();
            if(!respuesta.matches("^{0-9}[9]{A-Z}")){
                System.out.println("Formato del DNI invalido");
            } else {
                break;
            }
        }
    }

    public String getRespuesta(){
        return respuesta;
    }
}
