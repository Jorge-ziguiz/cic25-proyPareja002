package cic25.proyPareja002.grupo8.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cic25.proyPareja002.grupo8.app.exeptions.TiendaNoExistia;
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
    void testDelete() throws Exception {
        Tienda tienda = new Tienda();
        tienda.setNombre("Nueva tienda");
        tienda.setUbicacion("Dirección");

        tienda = tiendaRepository.save(tienda);

        Long id = tienda.getId();

        mockMvc.perform(delete("/tiendas/" + id))
                .andExpect(status().isOk());

        Optional<Tienda> eliminada = tiendaRepository.findById(id);
        assertTrue(eliminada.isEmpty());
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
        Tienda tienda = new Tienda();
        tienda.setNombre("nueva tienda");
        tienda.setUbicacion("Dirección de ejemplo 1");
        tiendaRepository.save(tienda);

        Tienda tienda2 = new Tienda();
        tienda2.setNombre("nueva tienda 2");
        tienda2.setUbicacion("Dirección de ejemplo 2");
        tiendaRepository.save(tienda2);

        Tienda tienda3 = new Tienda();
        tienda3.setNombre("nueva tienda 3");
        tienda3.setUbicacion("Dirección de ejemplo 3");
        tiendaRepository.save(tienda3);

        List<Tienda> tiendas = tiendaRepository.findAll();

        assertTrue(tiendas.size() > 0);
    }

    @Test
    void testPost() throws Exception {
        Tienda tienda = new Tienda();
        tienda.setNombre("Nueva tienda");
        tienda.setUbicacion("Dirección");

        String tiendaJson = objectMapper.writeValueAsString(tienda);

        mockMvc.perform(post("/tiendas")
                .contentType("application/json")
                .content(tiendaJson))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String respuesta = result.getResponse().getContentAsString();
                    Tienda tiendaCreada = objectMapper.readValue(respuesta, Tienda.class);

                    Optional<Tienda> tiendaRealmenteCreada = tiendaRepository.findById(tiendaCreada.getId());

                    assertTrue(tiendaRealmenteCreada.isPresent());
                });
    }

    @Test
    void testUpdate() throws Exception {
        Tienda tienda = new Tienda();
        tienda.setNombre("Nueva tienda");
        tienda.setUbicacion("Dirección");

        Long guardadaId = tiendaRepository.save(tienda).getId();

        tienda.setNombre("Nombre cambiado");
        tienda.setUbicacion("Prueba de cambio de dirección");

        tiendaRepository.save(tienda);

        String jsonActualizado = objectMapper.writeValueAsString(tienda);

        mockMvc.perform(put("/tiendas/" + guardadaId).contentType("application/json").content(jsonActualizado))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    Tienda actualizada = objectMapper.readValue(json, Tienda.class);
                    assertEquals("Nombre cambiado", actualizada.getNombre());
                    assertEquals("Prueba de cambio de dirección", actualizada.getUbicacion());
                });
    }

    @Test
    void testUpdateNotFound() throws Exception {
        Tienda tienda = new Tienda();
        Long id = tiendaRepository.save(tienda).getId();
        tiendaRepository.deleteById(id);

        Tienda tiendaSinId = new Tienda();
        tiendaSinId.setNombre("Tienda nueva");
        tiendaSinId.setUbicacion("Calle falsa 123");

        String jsonActualizado = objectMapper.writeValueAsString(tiendaSinId);

        mockMvc.perform(put("/tiendas/" + id)
                .contentType("application/json")
                .content(jsonActualizado))
                .andExpect(status().isNotFound());
    }
}
