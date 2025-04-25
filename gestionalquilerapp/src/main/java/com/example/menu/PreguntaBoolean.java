package com.example.menu;

public class PreguntaBoolean extends PreguntaSimple {

    private boolean respuesta;

    public PreguntaBoolean (String textoPregunta){
        super(textoPregunta);
    }

    public boolean getRespuesta() {
        return this.respuesta;
    }

    @Override
    protected void esperarRespuesta() {
        while(true) {
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("si")) {
                respuesta = true;
                break;
            } else if(input.equalsIgnoreCase("no")) {
                respuesta = false;
                break;
            }
            if(!esRespuestaValida()) {
                System.out.println("Por favor, introduce una respuesta válida.");
            } else {
                break; // Salir del bucle si la respuesta es válida
            }
        }
    }
    
    


    
}
