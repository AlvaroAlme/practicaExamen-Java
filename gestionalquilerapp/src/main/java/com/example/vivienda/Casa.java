/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.vivienda;

/**
 *
 * @author ALVARO
 */
public class Casa extends Vivienda {

    private boolean tieneJardin;
    private boolean tieneGaraje;
    private int costeJardin;

    public Casa() {
        super();
    }

    public Casa(String direccion, double metrosCuadrados, double precioAlquilerMensual, boolean tieneJardin, boolean tieneGaraje, int costeJardin) {
        super(direccion, metrosCuadrados, precioAlquilerMensual);
        this.tieneJardin = tieneJardin;
        this.tieneGaraje = tieneGaraje;
        this.costeJardin = costeJardin;
    }

    public boolean isTieneJardin() {
        return tieneJardin;
    }

    public void setTieneJardin(boolean tieneJardin) {
        this.tieneJardin = tieneJardin;
    }

    public boolean isTieneGaraje() {
        return tieneGaraje;
    }

    public void setTieneGaraje(boolean tieneGaraje) {
        this.tieneGaraje = tieneGaraje;
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

    @Override
    public String toString() {
        return "Casa [tieneJardin=" + tieneJardin + ", tieneGaraje=" + tieneGaraje + ", costeJardin=" + costeJardin
                + ", toString()=" + super.toString() + "]";
    }

    
   
}
