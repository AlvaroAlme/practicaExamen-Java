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
        
        //buscar dni propietario y recorrer hasta null
        for(int i = 0; i < listadoVivienda.length && !vivienda.equals(i); i++){
            listadoVivienda[i] = vivienda;
            return true;
        }
        return false;   
    }
    
    public String informacionVivienda(String dniPropietario){
        
       return null;
        
    }

    public void listadoViviendas() {

    }

    public boolean asignarAlquiler(String dniPropietario, Persona inquilino, double dineroInquilino) {
        return false;
    }

    public double calcularCosteSeguro(String dniPropietario) {
        return -1;
    }
    
    
}
