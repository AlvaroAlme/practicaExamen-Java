package com.example;

public class ViviendaConfig {

    private Persona propietario;
    private Persona inquilino;
    private int minCosteTotal;
    private int maxCosteTotal;

    public ViviendaConfig() {
        super();
        minCosteTotal = -1;
        maxCosteTotal = -1;
    }

    public Persona getPropietario() {
        return propietario;
    }
    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }
    public Persona getInquilino() {
        return inquilino;
    }
    public void setInquilino(Persona inquilino) {
        this.inquilino = inquilino;
    }
    public int getMinCosteTotal() {
        return minCosteTotal;
    }
    public void setMinCosteTotal(int minCosteTotal) {
        this.minCosteTotal = minCosteTotal;
    }
    public int getMaxCosteTotal() {
        return maxCosteTotal;
    }
    public void setMaxCosteTotal(int maxCosteTotal) {
        this.maxCosteTotal = maxCosteTotal;
    }

}
