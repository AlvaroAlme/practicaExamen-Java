package com.example;

import com.example.GestionAlquiler;
import com.example.vivienda.Apartamento;
import com.example.vivienda.Casa;
import com.example.vivienda.LocalComercial;
import com.example.vivienda.Vivienda;
import com.example.vivienda.Asegurable;
import com.example.Persona;
import java.util.Scanner;

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

        System.out.println("Introduce la direccion de la Vivienda");
        String direccionVivienda = scanner.nextLine();

        System.out.println("Introduce los m2 de la vivienda: ");
        double metrosCuadrados = scanner.nextDouble();

        System.out.println("Introduce el precio del alquiler mensual: ");
        double precioAlquilerMensual = scanner.nextDouble();

        
        int tipoVivienda = 0;
        System.out.println("Que tipo de vievienda quieres registrar?");
        System.out.println("1.Casa");
        System.out.println("2.Apartamento");
        System.out.println("3.Local Comnercial");

        switch (tipoVivienda) {
            case 1:
                System.out.println("La casa disponde de Jardin?");
                boolean tieneJardin = scanner.nextBoolean();
                break;
        
            default:
                break;
        }
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
        String dniInquilino = scanner.nextLine();

        System.out.println("Introduce la cantidad de la que dispone el inquilino: ");
        double dineroInquilino = scanner.nextDouble();

        boolean alquilerAsignado = gestionAlquiler.asignarAlquiler(dniPropietario, dniInquilino, dineroInquilino);
        if(alquilerAsignado){
            System.out.println("Se ha asignado el alquiler correctamente");
        } else {
            System.out.println("No se pudo alquilar la viienda");
        }

    }

    




}