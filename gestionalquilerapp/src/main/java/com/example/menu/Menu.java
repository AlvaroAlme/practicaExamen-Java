package com.example.menu;

public class Menu {

    private Menu() {
        // Constructor privado para evitar instanciación
    }

    /**
     * Pregunta que espera una respuesta de texto.
     * @param textoPregunta
     * @return la respuesta del usuario
     */
    public static String preguntarTexto(String textoPregunta) {
        PreguntaTexto pregunta = new PreguntaTexto(textoPregunta);
        pregunta.preguntar();
        return pregunta.getRespuesta();
    }

    /**
     * Pregunta que espera una respuesta de tipo entero.
     * @param textoPregunta
     * @return la respuesta del usuario
     */
    public static int preguntarEntero(String textoPregunta) {
        PreguntaEntero pregunta = new PreguntaEntero(textoPregunta);
        pregunta.preguntar();
        return pregunta.getRespuesta();
    }

    /**
     * Pregunta que espera una respuesta de tipo decimal.
     * @param textoPregunta
     * @return la respuesta del usuario
     */
    public static double preguntarDecimal(String textoPregunta) {
        PreguntaDecimal pregunta = new PreguntaDecimal(textoPregunta);
        pregunta.preguntar();
        return pregunta.getRespuesta();
    }

    /**
     * Pregunta que ofrece un conjunto de opciones y espera que el usuario escoja una.
     * @param textoPregunta
     * @return la opción elegida por el usuario (comenzando a contar desde el 1)
     */
    public static int preguntarOpcion(String[] opciones) {
        PreguntaOpciones pregunta = new PreguntaOpciones(opciones);
        pregunta.preguntar();
        return pregunta.getRespuesta();
    }

    /**
     * Pregunta que espera una respuesta de tipo entero positivo.
     * Esta pregunta es un ejemplo de cómo se pueden implementar validaciones.
     * @param textoPregunta
     * @return la respuesta del usuario
     */
    public static int preguntarEnteroPositivo(String textoPregunta) {
        PreguntaEnteroPositivo pregunta = new PreguntaEnteroPositivo(textoPregunta);
        pregunta.preguntar();
        return pregunta.getRespuesta();
    }
    
    public static boolean preguntaBoolean(String textoPregunta){
        PreguntaBoolean pregunta = new PreguntaBoolean(textoPregunta);
        pregunta.preguntar();
        return pregunta.getRespuesta();
    }
}
