/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.vivienda;

/**
 *
 * @author ALVARO
 */
public class LocalComercial extends Vivienda {
    
    private int recargoUbicacion;

    public LocalComercial() {
        super();
    }

    public LocalComercial(int recargoUbicacion, String direccion, double metrosCuadrados, double precioAlquilerMensual) {
        super(direccion, metrosCuadrados, precioAlquilerMensual);
        this.recargoUbicacion = recargoUbicacion;
    }

    public int getRecargoUbicacion() {
        return recargoUbicacion;
    }

    public void setRecargoUbicacion(int recargoUbicacion) {
        this.recargoUbicacion = recargoUbicacion;
    }
    
    @Override
    public double calcularCosteTotal(){
        return this.getPrecioAlquilerMensual() + this.getRecargoUbicacion();
    }
    
    @Override
    public double calcularCosteSeguro(){
     
            return (this.getMetrosCuadrados() * 0.8) + (this.getRecargoUbicacion() * 0.1);
                
}
    
}
