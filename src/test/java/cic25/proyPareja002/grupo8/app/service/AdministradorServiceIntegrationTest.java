package cic25.proyPareja002.grupo8.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cic25.proyPareja002.grupo8.app.model.Administrador;

@SpringBootTest
public class AdministradorServiceIntegrationTest {

    @Autowired
    AdministradorService administradorService;

    @Test
    void testCreate() {
        Administrador administrador = new Administrador();
        administrador.setNombre("admin 1");
        administrador.setUsuario("username");
        administrador.setVersion(1L);

        Administrador adminResultado = administradorService.create(administrador);
        assertEquals("admin 1", adminResultado.getNombre());
    }

    @Test
    void testDelete() {
        Administrador administrador = new Administrador();
        administrador.setNombre("admin 1");
        administrador.setUsuario("username");
        administrador.setVersion(1L);

        Long idAdminCreado = administradorService.create(administrador).getId();
        administradorService.delete(idAdminCreado);

        Optional<Administrador> adminBorrado = administradorService.get(idAdminCreado);

        assertTrue(adminBorrado.isEmpty());
    }

    @Test
    void testGet() {
        Administrador administrador = new Administrador();
        administrador.setNombre("admin 1");
        administrador.setUsuario("username");
        administrador.setVersion(1L);

        Long idAdminCreado = administradorService.create(administrador).getId();
        Optional<Administrador> adminGuardado = administradorService.get(idAdminCreado);

        assertTrue(adminGuardado.isPresent());
    }

    @Test
    void testGetAllAdministradores() {
        Administrador administrador = new Administrador();
        administrador.setNombre("admin 1");
        administrador.setUsuario("username");
        administrador.setVersion(1L);

        Administrador administrador2 = new Administrador();
        administrador2.setNombre("admin 1");
        administrador2.setUsuario("username");
        administrador2.setVersion(1L);

        Administrador administrador3 = new Administrador();
        administrador3.setNombre("admin 1");
        administrador3.setUsuario("username");
        administrador3.setVersion(1L);

        administradorService.create(administrador);
        administradorService.create(administrador2);
        administradorService.create(administrador3);

        List<Administrador> administradores = administradorService.getAllAdministradores();

        assertTrue(administradores.size() >= 3);
    }

    @Test
    void testUpdate() {
        Administrador administrador = new Administrador();
        administrador.setNombre("admin 1");
        administrador.setUsuario("username");
        administrador.setVersion(1L);

        Long id = administradorService.create(administrador).getId();

        administrador.setNombre("cambio de nombre");
        administrador.setUsuario("newUser");

        administrador = administradorService.update(id, administrador);

        assertEquals("cambio de nombre", administrador.getNombre());
        assertEquals("newUser", administrador.getUsuario());
    }
}
