package com.example;

import static org.junit.Assert.*;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Test;

import com.example.vivienda.Apartamento;
import com.example.vivienda.Casa;
import com.example.vivienda.LocalComercial;
import com.example.vivienda.Vivienda;
import com.github.javafaker.Faker;

public class GestionAlquilerTest {

    private final GestionAlquiler gestionAlquiler = new GestionAlquiler();
    private final char[] nifLetters = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    private int firstEmptyPosition = 0;

    @Test
    public void testRegistrarVivienda() {
        Persona persona = randomPersona();
        Vivienda vivienda = createVivienda(persona);
        assertTrue("No se ha podido registrar una vivienda cuando no había ninguna registrada.", gestionAlquiler.registrarVivienda(vivienda, persona));
        assertFalse("Se ha podido registrar una vivienda aunque el propietario ya había registrado otra.", gestionAlquiler.registrarVivienda(createVivienda(persona), persona));
        partiallyFill();
        assertTrue("No se ha podido registrar una vivienda cuando había algunas registradas pero el nuevo propietario no había registraddo ninguna.", gestionAlquiler.registrarVivienda(vivienda, persona));
        complete();
        assertFalse("Se ha podido registrar una vivienda aunque el registro estaba lleno (150 viviendas registradas)", gestionAlquiler.registrarVivienda(vivienda, persona));
    }

    @Test
    public void testInformacionVivienda() {
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        Persona persona = randomPersona();
        Vivienda vivienda = createVivienda(persona);
        assertNull("Resultado no nulo con ninguna vivienda registrada (debería ser nulo)", gestionAlquiler.informacionVivienda(persona.getDni()));
        listado[0] = vivienda;
        assertEquals("No se ha encontrado la única vivienda registrada", vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()));
        assertNull("Resultado no nulo con una única vivienda registrada, pero no incluyendo el propietario", gestionAlquiler.informacionVivienda(generateValidNif()));
        partiallyFill();
        listado[firstEmptyPosition] = vivienda;
        assertEquals("No se ha encontrado una vivienda registrada al final", vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()));
        assertNull("Resultado no nulo con algunos registros, pero no incluyendo el propietario", gestionAlquiler.informacionVivienda(generateValidNif()));
        listado[firstEmptyPosition] = null;
        listado[firstEmptyPosition / 2] = vivienda;
        assertEquals("No se ha encontrado una vivienda registrada en el medio", vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()));
        assertNull("Resultado no nulo con algunos registros, pero no incluyendo el propietario", gestionAlquiler.informacionVivienda(generateValidNif()));
        listado[firstEmptyPosition / 2] = createVivienda();
        listado[0] = vivienda;
        assertEquals("No se ha encontrado una vivienda registrada al principio", vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()));
        assertNull("Resultado no nulo con algunos registros, pero no incluyendo el propietario", gestionAlquiler.informacionVivienda(generateValidNif()));
        complete();
        assertNull("Resultado no nulo con todos los registros completos, pero no incluyendo el propietario", gestionAlquiler.informacionVivienda(persona.getDni()));
    }

    @Test
    public void testAsignarAlquiler() {

    }

    @Test
    public void calcularCosteSeguro() {

    }

    @After
    public void clean() {
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        for(int i = 0; i < listado.length; i++) {
            listado[i] = null;
        }
        firstEmptyPosition = 0;
    }

    private void partiallyFill() {
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        int max = new Faker().random().nextInt(listado.length - 1);
        int i;
        for(i = 0; i < max; i++) {
            listado[i] = createVivienda();
        }
        firstEmptyPosition = i;
    }

    private void complete() {
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        for(int i = 0; i < listado.length; i++) {
            listado[i] = createVivienda();
        }
    }

    private Vivienda createVivienda() {
        Faker faker = new Faker();
        int type = faker.random().nextInt(1, 3);
        switch(type) {
            case 1: return createCasa();
            case 2: return createApartamento();
            case 3: return createLocalComercial();
            default: return null;
        }
    }

    private Vivienda createVivienda(Persona propietario) {
        Vivienda vivienda = createVivienda();
        vivienda.setPropietaria(propietario);
        return vivienda;
    }

    private Casa createCasa() {
        Faker faker = new Faker();
        Casa casa = new Casa();
        fill(casa);
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

    private Apartamento createApartamento() {
        Faker faker = new Faker();
        Apartamento apartamento = new Apartamento();
        apartamento.setCosteMantenimiento(faker.random().nextInt(10, 200));
        fill(apartamento);
        return apartamento;
    }

    private LocalComercial createLocalComercial() {
        Faker faker = new Faker();
        LocalComercial localComercial = new LocalComercial();
        localComercial.setRecargoUbicacion(faker.random().nextInt(50, 300));
        fill(localComercial);
        return localComercial;
    }

    private void fill(Vivienda vivienda) {
        Faker faker = new Faker();
        vivienda.setDireccion(faker.address().fullAddress());
        vivienda.setIsDisponible(true);
        vivienda.setMetrosCuadrados(faker.random().nextInt(40, 200));
        vivienda.setPrecioAlquilerMensual(faker.random().nextInt(300, 1000));
        vivienda.setPropietaria(randomPersona());
    }

    private Persona randomPersona() {
        Faker faker = new Faker();
        Persona persona = new Persona();
        persona.setNombre(faker.name().firstName());
        persona.setApellidos(faker.name().lastName());
        persona.setDni(generateValidNif());
        return persona;
    }
    
    private String generateValidNif() {
        Random random = new Random();
        int dni = random.nextInt(100000000);
        char letra = nifLetters[dni % 23];
        return StringUtils.leftPad(String.valueOf(dni), 8, "0") + letra;
    }
}
