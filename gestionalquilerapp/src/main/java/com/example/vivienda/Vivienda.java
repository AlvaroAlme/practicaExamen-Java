/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.vivienda;

import com.example.Persona;

/**
 *
 * @author ALVARO
 */
public abstract class Vivienda implements Asegurable {
    
    private String direccion;
    private double metrosCuadrados;
    private double precioAlquilerMensual;
    private boolean isDisponible = true;
    private Persona propietaria;
    private Persona inquilina = null;

    protected Vivienda() {
        super();
    }

    protected Vivienda(String direccion, double metrosCuadrados, double precioAlquilerMensual) {
        this.direccion = direccion;
        this.metrosCuadrados = metrosCuadrados;
        this.precioAlquilerMensual = precioAlquilerMensual;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public double getPrecioAlquilerMensual() {
        return precioAlquilerMensual;
    }

    public void setPrecioAlquilerMensual(double precioAlquilerMensual) {
        this.precioAlquilerMensual = precioAlquilerMensual;
    }

    public boolean isIsDisponible() {
        return isDisponible;
    }

    public void setIsDisponible(boolean isDisponible) {
        this.isDisponible = isDisponible;
    }

    public Persona getPropietaria() {
        return propietaria;
    }

    public void setPropietaria(Persona propietaria) {
        this.propietaria = propietaria;
    }

    public Persona getInquilina() {
        return inquilina;
    }

    public void setInquilina(Persona inquilino) {
        this.inquilina = inquilino;
    }
    
    public abstract double calcularCosteTotal();
    
    public String informacionComun(){
        
        return "Direccion: " + this.getDireccion() + "/n" + "Metros Cuadrados: " +  this.getMetrosCuadrados() + "/n" + "Precio del alquiler mensual: " + this.getPrecioAlquilerMensual() + "/n" + "Propietaria/o: " + this.getPropietaria() + "/n" + "Inquilino/a: " +this.getInquilina();
    }
    
}
