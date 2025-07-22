package cic25.proyPareja002.grupo8.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;

import cic25.proyPareja002.grupo8.app.model.Tienda;
import cic25.proyPareja002.grupo8.app.repository.TiendaRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TiendaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TiendaRepository tiendaRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testDelete() {

    }

    @Test
    void testGet() throws Exception {
        Tienda tienda = new Tienda();
        tienda.setNombre("Nueva tienda");
        tienda.setUbicacion("Dirección");

        tienda = tiendaRepository.save(tienda);

        mockMvc.perform(get("/tiendas/" + tienda.getId()).contentType("application.json"))

        .andExpect(result -> {
            String json = result.getResponse().getContentAsString();
            Tienda recibida = objectMapper.readValue(json, Tienda.class);
            assertEquals("Nueva tienda", recibida.getNombre());
            assertEquals("Dirección", recibida.getUbicacion());
        });
    }

    @Test
    void testGetAll() {
        
    }

    @Test
    void testPost() {

    }

    @Test
    void testUpdate() {

    }
}
