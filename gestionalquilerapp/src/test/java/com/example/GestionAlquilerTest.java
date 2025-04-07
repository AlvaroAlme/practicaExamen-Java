package com.example;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import com.example.vivienda.Vivienda;
import com.github.javafaker.Faker;

public class GestionAlquilerTest {

    private final GestionAlquiler gestionAlquiler = new GestionAlquiler();
    private final TestUtils testUtils = new TestUtils();

    private int firstEmptyPosition = 0;

    @Test
    public void testRegistrarVivienda() {
        ViviendaConfig config = new ViviendaConfig();
        config.setPropietario(testUtils.persona());
        Vivienda vivienda = testUtils.createVivienda(config);

        assertTrue("No se ha podido registrar una vivienda cuando no había ninguna registrada.", 
        gestionAlquiler.registrarVivienda(vivienda, config.getPropietario()));

        assertFalse("Se ha podido registrar una vivienda aunque el propietario ya había registrado otra.", 
        gestionAlquiler.registrarVivienda(testUtils.createVivienda(config), config.getPropietario()));

        partiallyFill();
        assertTrue("No se ha podido registrar una vivienda cuando había algunas registradas pero el nuevo propietario no había registraddo ninguna.", 
        gestionAlquiler.registrarVivienda(vivienda, config.getPropietario()));

        fill();
        assertFalse("Se ha podido registrar una vivienda aunque el registro estaba lleno (150 viviendas registradas)", 
        gestionAlquiler.registrarVivienda(vivienda, config.getPropietario()));
    }

    @Test
    public void testInformacionVivienda() {
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        Persona persona = testUtils.persona();
        ViviendaConfig config = new ViviendaConfig();
        config.setPropietario(persona);
        Vivienda vivienda = testUtils.createVivienda(config);

        assertNull("Resultado no nulo con ninguna vivienda registrada (debería ser nulo)", gestionAlquiler.informacionVivienda(persona.getDni()));

        listado[0] = vivienda;
        assertEquals("No se ha encontrado la única vivienda registrada", vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()));
        assertNull("Resultado no nulo con una única vivienda registrada, pero no incluyendo el propietario", gestionAlquiler.informacionVivienda(testUtils.generateValidNif()));

        partiallyFill();
        listado[firstEmptyPosition] = vivienda;
        assertEquals("No se ha encontrado una vivienda registrada al final", vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()));
        assertNull("Resultado no nulo con algunos registros, pero no incluyendo el propietario", gestionAlquiler.informacionVivienda(testUtils.generateValidNif()));

        listado[firstEmptyPosition] = null;
        listado[firstEmptyPosition / 2] = vivienda;
        assertEquals("No se ha encontrado una vivienda registrada en el medio", vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()));
        assertNull("Resultado no nulo con algunos registros, pero no incluyendo el propietario", gestionAlquiler.informacionVivienda(testUtils.generateValidNif()));

        listado[firstEmptyPosition / 2] = testUtils.createVivienda();
        listado[0] = vivienda;
        assertEquals("No se ha encontrado una vivienda registrada al principio", vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()));
        assertNull("Resultado no nulo con algunos registros, pero no incluyendo el propietario", gestionAlquiler.informacionVivienda(testUtils.generateValidNif()));

        fill();
        assertNull("Resultado no nulo con todos los registros completos, pero no incluyendo el propietario", gestionAlquiler.informacionVivienda(persona.getDni()));
    }

    @Test
    public void testAsignarAlquiler() {
        Faker faker = new Faker();
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        ViviendaConfig configMin = new ViviendaConfig();
        configMin.setMinCosteTotal(2000);
        configMin.setMaxCosteTotal(3000);
        fill(configMin);
        ViviendaConfig configMax = new ViviendaConfig();
        configMax.setMaxCosteTotal(1000);
        configMax.setMinCosteTotal(500);

        assertFalse("Se ha podido asignar el alquiler de una vivienda cuando el dinero disponible no superaba el coste total del alquiler.",
            gestionAlquiler.asignarAlquiler(listado[faker.random().nextInt(0, 150)].getPropietaria().getDni(), testUtils.persona(), 1800.0));

        Vivienda vivienda = testUtils.createVivienda(configMax);
        listado[faker.random().nextInt(0, 150)] = vivienda;
        assertTrue("No se ha podido asignar el alquiler de una vivienda cuando el dinero disponible superaba el coste total del alquiler.",
            gestionAlquiler.asignarAlquiler(vivienda.getPropietaria().getDni(), testUtils.persona(), 1800.0));
        assertFalse("Se ha podido asignar el alquiler de una vivienda cuando la vivienda ya no estaba disponible.",
            gestionAlquiler.asignarAlquiler(vivienda.getPropietaria().getDni(), testUtils.persona(), 1800.0));
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

    private void partiallyFill(ViviendaConfig config) {
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        int max = new Faker().random().nextInt(listado.length - 1);
        int i;
        for(i = 0; i < max; i++) {
            listado[i] = testUtils.createVivienda(config);
        }
        firstEmptyPosition = i;
    }

    private void partiallyFill() {
        partiallyFill(new ViviendaConfig());
    }

    private void fill(ViviendaConfig config) {
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        for(int i = 0; i < listado.length; i++) {
            listado[i] = testUtils.createVivienda(config);
        }
    }

    private void fill() {
        fill(new ViviendaConfig());
    }

    
}
