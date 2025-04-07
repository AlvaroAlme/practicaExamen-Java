package com.example;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.example.vivienda.Apartamento;
import com.example.vivienda.Casa;
import com.example.vivienda.LocalComercial;
import com.example.vivienda.Vivienda;
import com.github.javafaker.Faker;

public class TestUtils {

    private final char[] nifLetters = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
    
    public Vivienda createVivienda(ViviendaConfig config) {
        Faker faker = new Faker();
        int type = faker.random().nextInt(1, 3);
        switch(type) {
            case 1: return createCasa(config);
            case 2: return createApartamento(config);
            case 3: return createLocalComercial(config);
            default: return null;
        }
    }

    public Vivienda createVivienda() {
        return createVivienda(new ViviendaConfig());
    }

    public Casa createCasa(ViviendaConfig config) {
        Faker faker = new Faker();
        Casa casa = new Casa();
        fill(casa, config);
        if(faker.random().nextBoolean()) {
            casa.setTieneJardin(true);
            casa.setCosteJardin(faker.random().nextInt(20, 100));
        } else {
            casa.setTieneJardin(false);
            casa.setCosteJardin(0);
        }
        casa.setTieneGaraje(faker.random().nextBoolean());
        return casa;
    }

    public Casa createCasa() {
        return createCasa(new ViviendaConfig());
    }

    public Apartamento createApartamento(ViviendaConfig config) {
        Faker faker = new Faker();
        Apartamento apartamento = new Apartamento();
        apartamento.setCosteMantenimiento(faker.random().nextInt(10, 200));
        fill(apartamento, config);
        return apartamento;
    }

    public Apartamento createApartamento() {
        return createApartamento(new ViviendaConfig());
    }

    public LocalComercial createLocalComercial(ViviendaConfig config) {
        Faker faker = new Faker();
        LocalComercial localComercial = new LocalComercial();
        localComercial.setRecargoUbicacion(faker.random().nextInt(50, 300));
        fill(localComercial, config);
        return localComercial;
    }

    public LocalComercial createLocalComercial() {
        return createLocalComercial(new ViviendaConfig());
    }

    public Persona persona() {
        Faker faker = new Faker();
        Persona persona = new Persona();
        persona.setNombre(faker.name().firstName());
        persona.setApellidos(faker.name().lastName());
        persona.setDni(generateValidNif());
        return persona;
    }
    
    public String generateValidNif() {
        Random random = new Random();
        int dni = random.nextInt(100000000);
        char letra = nifLetters[dni % 23];
        return StringUtils.leftPad(String.valueOf(dni), 8, "0") + letra;
    }

    private void fill(Vivienda vivienda, ViviendaConfig config) {
        Faker faker = new Faker();
        if(config.getPropietario() == null) {
            config.setPropietario(persona());
        }
        if(config.getMinCosteTotal() < 0) {
            config.setMinCosteTotal(300);
        }
        if(config.getMaxCosteTotal() <= config.getMinCosteTotal()) {
            config.setMaxCosteTotal(config.getMinCosteTotal() + 500);
        } else if(config.getMaxCosteTotal() < 0) {
            config.setMaxCosteTotal(1000);
        }
        vivienda.setDireccion(faker.address().fullAddress());
        vivienda.setIsDisponible(true);
        vivienda.setMetrosCuadrados(faker.random().nextInt(40, 200));
        vivienda.setPrecioAlquilerMensual(faker.random().nextInt(config.getMinCosteTotal(), config.getMaxCosteTotal()));
        vivienda.setPropietaria(config.getPropietario());
    }

}
