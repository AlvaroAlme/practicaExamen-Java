package com.example;

import com.example.menu.Menu;
import com.example.vivienda.Apartamento;
import com.example.vivienda.Casa;
import com.example.vivienda.LocalComercial;
import com.example.vivienda.Vivienda;

public class Principal {
    public static void main(String[] args) {
        GestionAlquiler gestionAlquiler = new GestionAlquiler();

        int opcion = 0;

        while(opcion != 6){
        
        String[] opciones = {"Registrar una vivienda", "Buscar una vivienda", "Listar vivienda", "Calcular coste del seguro de una vivienda", "Alquilar una vivienda", "Salir de la aplicacion"};
        opcion = Menu.preguntarOpcion(opciones);

        
        switch (opcion) {
            case 1:
                registrarVivienda(gestionAlquiler);
            break;
            case 2:
                buscarVivienda(gestionAlquiler);
            break;
            case 3:
                listarVivienda(gestionAlquiler);
            break;
            case 4:
                calcularCosteSeguro(gestionAlquiler);
            break;
            case 5:
                alquilerVivienda(gestionAlquiler);
            break;
            case 6:
                System.out.println("Adios!");
            break;

            default:
            break;
        }

    }
}

    public static void registrarVivienda(GestionAlquiler gestionAlquiler){

        String direccionVivienda = Menu.preguntarTexto("Introduce la direccion de la vivienda"); //TODO: LA DIRECCION NO MANEJA QUE HAYA UN ESPACIO O NUMEROS EN LA ENTRADA
        double metrosCuadrados = Menu.preguntarDecimal("Introduce los m2 de la vivienda:");

        double precioAlquilerMensual = Menu.preguntarDecimal("Introduce el precio del alquiler mensual: ");
        Vivienda vivienda = null;
        Persona persona = null;
        
        
        int tipoVivienda = Menu.preguntarOpcion(new String[]{"Casa", "Apartamento", "Local Comercial"});

        switch (tipoVivienda) {
            case 1:
                boolean tieneJardin = Menu.preguntaBoolean("La casa tiene jardin");
                int costeJardin = 0;
                if(tieneJardin){
                     costeJardin = Menu.preguntarEntero("Introduce el coste del jardin: ");
                }
                boolean tienGaraje = Menu.preguntaBoolean("¿La casa tiene garaje?");

                vivienda = new Casa(direccionVivienda, metrosCuadrados, precioAlquilerMensual, tieneJardin, tienGaraje, costeJardin);
                break;

            case 2:
                int costeMantenimiento = Menu.preguntarEntero("Introduce el coste de mantenimiento: ");
                boolean tieneAscensor = Menu.preguntaBoolean("¿El apartamento cuenta con Ascensor?");

                vivienda = new Apartamento(direccionVivienda, metrosCuadrados, precioAlquilerMensual, costeMantenimiento, tieneAscensor);
                break;
            case 3: 
                int recargoUbicacion = Menu.preguntarEntero("Introduce el recargo por Ubicacion: ");

                vivienda = new LocalComercial(recargoUbicacion, direccionVivienda, metrosCuadrados, precioAlquilerMensual);
                break;
            default:
                System.out.println("Por favor, elige una tipo de vivienda valido");
                break;

        }

        if(vivienda == null) {
            return;
        }

        String nombrePropietario = Menu.preguntarTexto("Introduce el nombre del propietario: ");
        String apellidosPropietario = Menu.preguntarTexto("Introduce el apellido del propietario: ");
        String dniPropietario = Menu.preguntarDNI("Introduce el DNI del propietario");
        
        
        persona = new Persona(nombrePropietario, apellidosPropietario, dniPropietario);
        vivienda.setPropietaria(persona);

        
        gestionAlquiler.registrarVivienda(vivienda, persona);
    
    }

    public static void listarVivienda(GestionAlquiler gestionAlquiler){

        System.out.println("Este es el listado de viviendas:");
        gestionAlquiler.listadoViviendas();
    }

    public static void buscarVivienda(GestionAlquiler gestionAlquiler){
        
        String dni = Menu.preguntarTexto("Introduce un DNI valido: ");
        String infoVivienda = gestionAlquiler.informacionVivienda(dni);

        if(infoVivienda != null){
            System.out.println(infoVivienda);
        } else {
            System.out.println("No se ha encontrado ninguna vivienda que coincida con el DNI");
        }
    }

    public static void calcularCosteSeguro(GestionAlquiler gestionAlquiler){

        
        String dni = Menu.preguntarTexto("Introduce un DNI valido: ");
        double costeSeguro = gestionAlquiler.calcularCosteSeguro(dni);

        if(costeSeguro != -1){
            System.out.println("El coste del seguro es: " + costeSeguro);
        } else {
            System.out.println("No se ha encontrado ninguna vivienda que coincida con el DNI");
        }
    }

    public static void alquilerVivienda(GestionAlquiler gestionAlquiler){
        String dniPropietario = Menu.preguntarTexto("Introduce un DNI valido: ");

        String nombreInquilino = Menu.preguntarTexto("Introduce el nombre del inquilino: ");
        String apellidoInquilino = Menu.preguntarTexto("Introduce el apellido del inquilino: ");
        String dniInquilino = Menu.preguntarTexto("Introduce el dni del inquilino: ");
        Persona inquilino = new Persona(nombreInquilino, apellidoInquilino, dniInquilino);

        double dineroInquilino = Menu.preguntarDecimal("Introduce la cantidad de la que dispone el inquilino: ");

        boolean alquilerAsignado = gestionAlquiler.asignarAlquiler(dniPropietario, inquilino, dineroInquilino);
        if(alquilerAsignado){
            System.out.println("Se ha asignado el alquiler correctamente");
        } else {
            System.out.println("No se pudo alquilar la viienda");
        }

    }

    




}