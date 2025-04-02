/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionalquilerapp.vivienda;

import com.mycompany.gestionalquilerapp.vivienda.Vivienda;

/**
 *
 * @author ALVARO
 */
public class Casa extends Vivienda {

    private boolean tieneJardin;
    private boolean tieneGaraje;
    private int costeJardin;

    public Casa(String direccion, double metrosCuadrados, double precioAlquilerMensual) {
        super(direccion, metrosCuadrados, precioAlquilerMensual);
    }

    public int getCosteJardin() {
        return costeJardin;
    }

    public void setCosteJardin(int costeJardin) {
        this.costeJardin = costeJardin;
    }

    @Override
    public double calcularCosteTotal() {

        return this.getPrecioAlquilerMensual() + this.getCosteJardin();
    }

    @Override
    public double calcularCosteSeguro() {
        
        if(tieneJardin && tieneGaraje){
           return this.getMetrosCuadrados() * 0.7 + 20 + 15;
        } else if(!tieneJardin && tieneGaraje) {
            return this.getMetrosCuadrados() * 0.7 + 15;
        } else if(tieneJardin && !tieneGaraje){
            return this.getMetrosCuadrados() * 0.7 + 20;
        } else {
            return this.getMetrosCuadrados() * 0.7;
        }
    }
   
}
