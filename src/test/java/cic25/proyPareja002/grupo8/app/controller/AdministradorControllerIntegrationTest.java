package cic25.proyPareja002.grupo8.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cic25.proyPareja002.grupo8.app.model.Administrador;
import cic25.proyPareja002.grupo8.app.repository.AdministradorRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AdministradorControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Test
    void testCreate() throws Exception {
        Administrador administrador = new Administrador();
        administrador.setNombre("mi administrador");
        administrador.setUsuario("user001");

        String adminJson = objectMapper.writeValueAsString(administrador);

        mockMvc.perform(post("/administrador")
                .contentType("application/json")
                .content(adminJson))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String respuesta = result.getResponse().getContentAsString();
                    Administrador adminCreado = objectMapper.readValue(respuesta, Administrador.class);

                    assertTrue(adminCreado.getId() > 0);

                    Optional<Administrador> adminRealmenteCreado = administradorRepository
                            .findById(adminCreado.getId());
                    assertTrue(adminRealmenteCreado.isPresent());
                });
    }

    @Test
    void testDelete() throws Exception {
        Administrador administrador = new Administrador();
        administrador.setNombre("mi administrador");
        administrador.setUsuario("user001");

        administrador = administradorRepository.save(administrador);

        Long id = administrador.getId();

        mockMvc.perform(delete("/administrador/" + id))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Optional<Administrador> adminBorrado = administradorRepository.findById(id);
                    assertTrue(adminBorrado.isEmpty());
                });
    }

    @Test
    void testGet() throws Exception {
        Administrador administrador = new Administrador();
        administrador.setNombre("mi administrador");
        administrador.setUsuario("user001");

        administrador = administradorRepository.save(administrador);

        mockMvc.perform(get("/administrador/" + administrador.getId())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    Administrador adminGuardado = objectMapper.readValue(json, Administrador.class);

                    assertTrue(adminGuardado.getId() > 0);
                    assertEquals("mi administrador", adminGuardado.getNombre());
                    assertEquals("user001", adminGuardado.getUsuario());
                });
    }

    @Test
    void testGetAllAdministradores() throws Exception {
        Administrador administrador = new Administrador();
        administrador.setNombre("mi administrador");
        administrador.setUsuario("user001");

        // administrador = administradorRepository.save(administrador);
        String administradorJson = objectMapper.writeValueAsString(administrador);
        mockMvc.perform(post("/administrador")
                .contentType("application/json")
                .content(administradorJson));

        mockMvc.perform(get("/administrador")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    List<Administrador> adminGuardados = objectMapper.readValue(json,
                            new TypeReference<List<Administrador>>() {

                            });
                    assertTrue(adminGuardados.size() > 0);
                });
    }

    @Test
    void testUpdate() throws Exception {
        Administrador administrador = new Administrador();
        administrador.setNombre("mi administrador");
        administrador.setUsuario("user001");
        administrador.setVersion(001L);

        // administrador = administradorRepository.save(administrador);

        String adminJson = objectMapper.writeValueAsString(administrador);
        
        MvcResult mvcResult = mockMvc.perform(post("/administrador")
                .contentType("application/json")
                .content(adminJson))
                .andReturn();

        Long id = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Administrador.class).getId();

        administrador.setNombre("nombre cambiado");
        administrador.setUsuario("usuario002");
        administrador.setId(id);

        // String adminJson = objectMapper.writeValueAsString(administrador);
        adminJson = objectMapper.writeValueAsString(administrador);

        mockMvc.perform(put("/administrador/" + id)
                .contentType("application/json")
                .content(adminJson))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    Administrador adminActualizado = objectMapper.readValue(json, Administrador.class);
                    assertEquals("nombre cambiado", adminActualizado.getNombre());
                });
    }

}
