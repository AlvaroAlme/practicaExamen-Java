package com.example.menu;

public class Ejemplo {

    public static void ejemplo() {
        String nombre = Menu.preguntarTexto("¿Cómo te llamas?");
        int edad = Menu.preguntarEntero("¿Cuántos años tienes?");
        double altura = Menu.preguntarDecimal("¿Cuánto mides en metros?");
        String[] opciones = {"Pizza", "Hamburguesa", "Kebab"};
        int opcion = Menu.preguntarOpcion(opciones);
        switch(opcion) {
            case 1:
                System.out.println("Has elegido Pizza.");
                break;
            case 2:
                System.out.println("Has elegido Hamburguesa.");
                break;
            case 3:
                System.out.println("Has elegido Kebab.");
                break;
            default:
                System.out.println("Opción no válida.");
        }
        System.out.println("Hola " + nombre + ", tienes " + edad + " años y mides " + altura + " metros.");
        System.out.println("Tu opción elegida es: " + opciones[opcion - 1]);
    }
    
}
