package com.example;

import java.util.Scanner;

import com.example.menu.Menu;
import com.example.vivienda.Apartamento;
import com.example.vivienda.Casa;
import com.example.vivienda.LocalComercial;
import com.example.vivienda.Vivienda;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionAlquiler gestionAlquiler = new GestionAlquiler();

        System.out.println("------Elige una opcion:------");
        System.out.println("1.Registrar una vivienda");
        System.out.println("2.Buscar una vivienda");
        System.out.println("3.Listar viviendas");
        System.out.println("4.Calcular el coste del seguro una vivienda");
        System.out.println("5.Alquilar una vivienda");
        System.out.println("6.Salir de la aplicacion.");

        int opcion = 0;

        switch (opcion) {
            case 1:
                registrarVivienda(scanner, gestionAlquiler);
            break;
            case 2:
                buscarVivienda(scanner, gestionAlquiler);
            break;
            case 3:
                listarVivienda(gestionAlquiler);
            break;
            case 4:
                calcularCosteSeguro(scanner, gestionAlquiler);
            break;
            case 5:
                alquilerVivienda(scanner, gestionAlquiler);
            break;
            case 6:
                System.out.println("Adios!");
            break;

            default:
            break;
        }

    }

    public static void registrarVivienda(Scanner scanner, GestionAlquiler gestionAlquiler){

        String direccionVivienda = Menu.preguntarTexto("Introduce la direccion de la vivienda");
        double metrosCuadrados = Menu.preguntarDecimal("Introduce los m2 de la vivienda:");

        System.out.println("Introduce el precio del alquiler mensual: ");
        double precioAlquilerMensual = scanner.nextDouble();
        Vivienda vivienda = null;
        Persona persona = null;
        
        
        int tipoVivienda = Menu.preguntarOpcion(new String[]{"Casa", "Apartamento", "Local Comercial"});

        switch (tipoVivienda) {
            case 1:
                boolean tieneJardin = Menu.preguntaBoolean("La casa tiene jardin");
                int costeJardin = 0;
                if(tieneJardin = true){
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
            default:
                System.out.println("Por favor, elige una tipo de vivienda valido");
                break;

        }

        String nombrePropietario = Menu.preguntarTexto("Introduce el nombre del propietario: ");
        String apellidosPropietario = Menu.preguntarTexto("Introduce el apellido del propietario: ");
        String dniPropietario = Menu.preguntarTexto("Introduce el DNI del propietario");
        if(!dniPropietario.matches("^[0-9]{8}[A-Z]$")){
            System.out.println("Formato del DNI invalido");
            return;
        }
        

        persona = new Persona(nombrePropietario, apellidosPropietario, dniPropietario);

        
        gestionAlquiler.registrarVivienda(vivienda, persona);
    
    }

    public static void listarVivienda(GestionAlquiler gestionAlquiler){
        System.out.println("Este es el listado de viviendas:");
        gestionAlquiler.listadoViviendas();
    }

    public static void buscarVivienda(Scanner scanner, GestionAlquiler gestionAlquiler){
        System.out.println("Introduce un DNI: ");
        String dni = scanner.nextLine();
        String infoVivienda = gestionAlquiler.informacionVivienda(dni);

        if(infoVivienda != null){
            System.out.println(infoVivienda);
        } else {
            System.out.println("No se ha encontrado ninguna vivienda que coincida con el DNI");
        }
    }

    public static void calcularCosteSeguro(Scanner scanner, GestionAlquiler gestionAlquiler){

        System.out.println("Introduce un DNI");
        String dni = scanner.nextLine();
        double costeSeguro = gestionAlquiler.calcularCosteSeguro(dni);

        if(costeSeguro != -1){
            System.out.println("El coste del seguro es: " + costeSeguro);
        } else {
            System.out.println("No se ha encontrado ninguna vivienda que coincida con el DNI");
        }
    }

    public static void alquilerVivienda(Scanner scanner, GestionAlquiler gestionAlquiler){
        System.out.println("Introduce el DNI del propietario: ");
        String dniPropietario = scanner.nextLine();

        System.out.println("Introduce el DNI del inquilino: ");
        Persona inquilino = null;

        System.out.println("Introduce la cantidad de la que dispone el inquilino: ");
        double dineroInquilino = scanner.nextDouble();

        boolean alquilerAsignado = gestionAlquiler.asignarAlquiler(dniPropietario, inquilino, dineroInquilino);
        if(alquilerAsignado){
            System.out.println("Se ha asignado el alquiler correctamente");
        } else {
            System.out.println("No se pudo alquilar la viienda");
        }

    }

    




}