package cic25.proyPareja002.grupo8.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cic25.proyPareja002.grupo8.app.model.Administrador;
import cic25.proyPareja002.grupo8.app.repository.AdministradorRepository;

@SpringBootTest
public class AdministradorServiceIntegrationTest {

    @Autowired
    AdministradorService administradorService;

    @Autowired
    AdministradorRepository administradorRepository;

    @Test
    void testCreate() {
        Administrador administrador = new Administrador();
        administrador.setNombre("admin 1");
        administrador.setUsuario("username");
        administrador.setVersion(1L);

        Administrador adminResultado = administradorService.create(administrador);
        assertEquals(administrador.getNombre(), adminResultado);
    }

    @Test
    void testDelete() {

    }

    @Test
    void testGet() {

    }

    @Test
    void testGetAllAdministradores() {

    }

    @Test
    void testUpdate() {

    }
}
