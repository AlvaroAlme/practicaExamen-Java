package com.example.menu;

/**
 * Una pregunta que espera una respuesta de tipo decimal.
 */
public class PreguntaDecimal extends PreguntaSimple {

    private double respuesta;

    public PreguntaDecimal(String textoPregunta) {
        super(textoPregunta);
    }

    @Override
    protected void esperarRespuesta() {
        while (true) {
            try {
                respuesta = Double.parseDouble(scanner.nextLine());
                if (!esRespuestaValida()) {
                    System.out.println("Por favor, introduce un número decimal válido.");
                } else {
                    break; // Salir del bucle si la respuesta es válida
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número decimal válido.");
            }
        }
    }

    public double getRespuesta() {
        return respuesta;
    }
    
}
