/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import com.example.vivienda.Vivienda;

/**
 *
 * @author ALVARO
 */
public class GestionAlquiler {

    private Vivienda[] listadoVivienda;

    public GestionAlquiler() {
        super();
        listadoVivienda = new Vivienda[150];
    }

    public Vivienda[] getListadoVivienda() {
        return this.listadoVivienda;
    }

    public boolean registrarVivienda(Vivienda vivienda, Persona persona) {

        
        for (int i = 0; i < listadoVivienda.length; i++) {
            if (listadoVivienda[i] == null) {
                listadoVivienda[i] = vivienda;
                return true;
            }
                else if (listadoVivienda[i].getDireccion().equals(vivienda.getDireccion())) {
                    throw new IllegalArgumentException("Ya existe una vivienda con esta direccion");
                } else if (listadoVivienda[i].getPropietaria().getDni().equals(persona.getDni())) {
                    throw new IllegalArgumentException("Ya existe una viivienda con este prepoietario");
                } 
        }
        return false;
    }

    public String informacionVivienda(String dni) {
        for (int i = 0; i < listadoVivienda.length; i++) {
            if (listadoVivienda[i].getPropietaria().getDni().equals(dni)) {
                return listadoVivienda[i].toString();
            }
        }
        return null;
    }

    public void listadoViviendas() {
        for (int i = 0; i < listadoVivienda.length; i++) {
            System.out.println(listadoVivienda[i].informacionComun());
        }

    }

    public boolean asignarAlquiler(String dniPropietario, Persona inquilino, double dineroInquilino) {
        for(int i = 0; i < listadoVivienda.length; i++) {
            // JUANJO: NullPointerException aquí
            if (listadoVivienda[i].getPropietaria().getDni().equals(dniPropietario) && listadoVivienda[i].isIsDisponible()) {
                if (dineroInquilino >= listadoVivienda[i].calcularCosteTotal()) {
                    listadoVivienda[i].setInquilina(inquilino);
                    listadoVivienda[i].setIsDisponible(false);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public double calcularCosteSeguro(String dniPropietario) {
         for (int i = 0; i < listadoVivienda.length; i++) {
            if (listadoVivienda[i].getPropietaria().getDni().equals(dniPropietario)) {
                return listadoVivienda[i].calcularCosteSeguro();
            }
        }
        return -1;
    }

}
