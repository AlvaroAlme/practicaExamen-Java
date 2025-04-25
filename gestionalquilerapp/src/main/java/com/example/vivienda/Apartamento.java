/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.vivienda;

/**
 *
 * @author ALVARO
 */
public class Apartamento extends Vivienda {
    
    private int costeMantenimiento;
    private boolean tieneAscensor;

    public Apartamento() {
        super();
    }

    public Apartamento(String direccion, double metrosCuadrados, double precioAlquilerMensual, int costeMantenimiento, boolean tieneAscensor) {
        super(direccion, metrosCuadrados, precioAlquilerMensual);
        this.costeMantenimiento = costeMantenimiento;
        this.tieneAscensor = tieneAscensor;
    }

    public int getCosteMantenimiento() {
        return costeMantenimiento;
    }

    public void setCosteMantenimiento(int costeMantenimiento) {
        this.costeMantenimiento = costeMantenimiento;
    }
    
    
    
    @Override
    
    public double calcularCosteTotal(){
        
        return this.getPrecioAlquilerMensual() + this.getCosteMantenimiento();
        
    }
    
    @Override
    public double calcularCosteSeguro(){
        
        return this.getMetrosCuadrados() * 0.5;
    }
    
}
