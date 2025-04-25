package com.example;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

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

        assertTrue(gestionAlquiler.registrarVivienda(vivienda, config.getPropietario()),
            "No se ha podido registrar una vivienda cuando no había ninguna registrada.");

        assertThrows(IllegalArgumentException.class, () -> gestionAlquiler.registrarVivienda(testUtils.createVivienda(config), config.getPropietario()),
            "Se ha podido registrar una vivienda aunque el propietario ya había registrado otra.");

        partiallyFill();
        assertTrue(gestionAlquiler.registrarVivienda(vivienda, config.getPropietario()),
            "No se ha podido registrar una vivienda cuando había algunas registradas pero el nuevo propietario no había registrado ninguna.");

        fill();
        assertFalse(gestionAlquiler.registrarVivienda(vivienda, config.getPropietario()),
            "Se ha podido registrar una vivienda aunque el registro estaba lleno (150 viviendas registradas).");
    }

    @Test
    public void testInformacionVivienda() {
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        Persona persona = testUtils.persona();
        ViviendaConfig config = new ViviendaConfig();
        config.setPropietario(persona);
        Vivienda vivienda = testUtils.createVivienda(config);

        assertNull(gestionAlquiler.informacionVivienda(persona.getDni()),
            "Resultado no nulo con ninguna vivienda registrada (debería ser nulo).");

        listado[0] = vivienda;
        assertEquals(vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()),
            "No se ha encontrado la única vivienda registrada.");
        assertNull(gestionAlquiler.informacionVivienda(testUtils.generateValidNif()),
            "Resultado no nulo con una única vivienda registrada, pero no incluyendo el propietario.");

        partiallyFill();
        listado[firstEmptyPosition] = vivienda;
        assertEquals(vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()),
            "No se ha encontrado una vivienda registrada al final.");
        assertNull(gestionAlquiler.informacionVivienda(testUtils.generateValidNif()),
            "Resultado no nulo con algunos registros, pero no incluyendo el propietario.");

        listado[firstEmptyPosition] = null;
        listado[firstEmptyPosition / 2] = vivienda;
        assertEquals(vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()),
            "No se ha encontrado una vivienda registrada en el medio.");
        assertNull(gestionAlquiler.informacionVivienda(testUtils.generateValidNif()),
            "Resultado no nulo con algunos registros, pero no incluyendo el propietario.");

        listado[firstEmptyPosition / 2] = testUtils.createVivienda();
        listado[0] = vivienda;
        assertEquals(vivienda.toString(), gestionAlquiler.informacionVivienda(persona.getDni()),
            "No se ha encontrado una vivienda registrada al principio.");
        assertNull(gestionAlquiler.informacionVivienda(testUtils.generateValidNif()),
            "Resultado no nulo con algunos registros, pero no incluyendo el propietario.");

        fill();
        assertNull(gestionAlquiler.informacionVivienda(persona.getDni()),
            "Resultado no nulo con todos los registros completos, pero no incluyendo el propietario.");
    }

    @Test
    public void testListadoVivienda() {
        fill();
        gestionAlquiler.listadoViviendas();
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

        assertFalse(gestionAlquiler.asignarAlquiler(listado[faker.random().nextInt(0, 150)].getPropietaria().getDni(), testUtils.persona(), 1800.0),
            "Se ha podido asignar el alquiler de una vivienda cuando el dinero disponible no superaba el coste total del alquiler.");

        Vivienda vivienda = testUtils.createVivienda(configMax);
        listado[faker.random().nextInt(0, 150)] = vivienda;
        assertTrue(gestionAlquiler.asignarAlquiler(vivienda.getPropietaria().getDni(), testUtils.persona(), 1800.0),
            "No se ha podido asignar el alquiler de una vivienda cuando el dinero disponible superaba el coste total del alquiler.");
        assertFalse(gestionAlquiler.asignarAlquiler(vivienda.getPropietaria().getDni(), testUtils.persona(), 1800.0),
            "Se ha podido asignar el alquiler de una vivienda cuando la vivienda ya no estaba disponible.");
    }

    @Test
    public void calcularCosteSeguro() {

    }

    @AfterEach
    public void clean() {
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        for (int i = 0; i < listado.length; i++) {
            listado[i] = null;
        }
        firstEmptyPosition = 0;
    }

    private void partiallyFill(ViviendaConfig config) {
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        int max = new Faker().random().nextInt(listado.length - 1);
        int i;
        for (i = 0; i < max; i++) {
            listado[i] = testUtils.createVivienda(config);
        }
        firstEmptyPosition = i;
    }

    private void partiallyFill() {
        partiallyFill(new ViviendaConfig());
    }

    private void fill(ViviendaConfig config) {
        Vivienda[] listado = gestionAlquiler.getListadoVivienda();
        for (int i = 0; i < listado.length; i++) {
            listado[i] = testUtils.createVivienda(config);
        }
    }

    private void fill() {
        fill(new ViviendaConfig());
    }
}