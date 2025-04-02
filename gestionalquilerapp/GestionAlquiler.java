/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionalquilerapp;

import com.mycompany.gestionalquilerapp.vivienda.Vivienda;


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
   
    public boolean registrarVivienda(Vivienda vivienda, Persona persona) {
        
        //buscar dni propietario y recorrer hasta null
        for(int i = 0; i < listadoVivienda.length && !vivienda.equals(i); i++){
            listadoVivienda[i] = vivienda;
            return true;
        }
            
    }
    
    public String informacionVivienda(Persona dni){
        
       return 
        
    }
    
    
}
