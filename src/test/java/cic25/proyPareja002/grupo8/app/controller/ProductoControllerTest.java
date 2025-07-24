package cic25.proyPareja002.grupo8.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cic25.proyPareja002.grupo8.app.model.Producto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoControllerTest {

        @Autowired
        MockMvc mockMvc;

        @Autowired
        ObjectMapper objectMapper;

        @Test
        void testCreate() throws Exception {
                Producto producto = new Producto();
                producto.setNombre("Arroz");
                producto.setMarca("Alguna Marca");
                producto.setPrecio(2.2);

                String ProductoJson = objectMapper.writeValueAsString(producto);

                mockMvc.perform(post("/producto")
                                .contentType("application/json")
                                .content(ProductoJson))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(result -> {
                                        String JsonResultadoPost = result.getResponse().getContentAsString();
                                        Producto ProductoResultado = objectMapper.readValue(JsonResultadoPost,
                                                        Producto.class);
                                        assertTrue(ProductoResultado.getId() > 0);
                                }).andDo(print());

        }

        @Test
        void testCreateNoAllowWithID() throws Exception {
                Producto producto = new Producto();
                producto.setId(Long.valueOf(1));
                producto.setNombre("Arroz");
                producto.setMarca("Alguna Marca");
                producto.setPrecio(2.2);

                String ProductoJson = objectMapper.writeValueAsString(producto);

                mockMvc.perform(post("/producto")
                                .contentType("application/json")
                                .content(ProductoJson))
                                .andExpect(status().is4xxClientError())
                                .andDo(print());

        }

        @Test
        void testDelete() throws Exception {
                Producto producto = new Producto();
                producto.setNombre("Arroz");
                producto.setMarca("Alguna Marca");
                producto.setPrecio(2.2);

                String ProductoJson = objectMapper.writeValueAsString(producto);

                String JsonResultadoPost = mockMvc.perform(post("/producto")
                                .contentType("application/json")
                                .content(ProductoJson))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Producto ProductoResultado = objectMapper.readValue(JsonResultadoPost, Producto.class);

                mockMvc.perform(delete("/producto/" + ProductoResultado.getId()))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                mockMvc.perform(get("/producto/" + ProductoResultado.getId()))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(result -> {
                                        String JsonResultadoGet = result.getResponse().getContentAsString();
                                        assertTrue(JsonResultadoGet.isEmpty());
                                }).andDo(print());

        }

        @Test
        void testGetAll() throws Exception {
                Producto producto = new Producto();
                producto.setNombre("Arroz");
                producto.setMarca("Alguna Marca");
                producto.setPrecio(2.2);

                String ProductoJson = objectMapper.writeValueAsString(producto);

                mockMvc.perform(post("/producto")
                                .contentType("application/json")
                                .content(ProductoJson))
                                .andExpect(status().isOk())
                                .andDo(print());

                mockMvc.perform(get("/producto"))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(result -> {
                                        String JsonResultadoGet = result.getResponse().getContentAsString();
                                        List<Producto> ResultadoGetAll = objectMapper.readValue(JsonResultadoGet,
                                                        new TypeReference<List<Producto>>() {
                                                        });
                                        assertTrue(!ResultadoGetAll.isEmpty());
                                        assertEquals(ResultadoGetAll.getFirst().getNombre(), producto.getNombre());

                                }).andDo(print());
        }

        @Test
        void testGetById() throws Exception {

                Producto producto = new Producto();
                producto.setNombre("Arroz");
                producto.setMarca("Alguna Marca");
                producto.setPrecio(2.2);

                String ProductoJson = objectMapper.writeValueAsString(producto);

                String JsonResultadoPost = mockMvc.perform(post("/producto")
                                .contentType("application/json")
                                .content(ProductoJson))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Producto ProductoResultado = objectMapper.readValue(JsonResultadoPost, Producto.class);

                mockMvc.perform(get("/producto/" + ProductoResultado.getId()))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(result -> {
                                        String JsonResultadoGet = result.getResponse().getContentAsString();
                                        Producto ProductoResultadoGet = objectMapper.readValue(JsonResultadoGet,
                                                        Producto.class);
                                        assertEquals(ProductoResultadoGet.getNombre(), ProductoResultado.getNombre());
                                }).andDo(print());

        }

        @Test
        void testUpdate() throws Exception {

                Producto producto = new Producto();
                producto.setNombre("Arroz");
                producto.setMarca("Alguna Marca");

                String ProductoJson = objectMapper.writeValueAsString(producto);

                String JsonResultadoPost = mockMvc.perform(post("/producto")
                                .contentType("application/json")
                                .content(ProductoJson))
                                .andExpect(status().isOk())
                                .andDo(print()).andReturn().getResponse().getContentAsString();

                Producto productoActualizado = objectMapper.readValue(JsonResultadoPost, Producto.class);
                productoActualizado.setPrecio(2.2);
                String JSonProductoActualizado = objectMapper.writeValueAsString(productoActualizado);

                mockMvc.perform(put("/producto")
                                .contentType("application/json")
                                .content(JSonProductoActualizado))
                                .andExpect(status().isOk())
                                .andDo(print());

                mockMvc.perform(get("/producto/" + productoActualizado.getId()))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(result -> {
                                        String JsonResultadoGet = result.getResponse().getContentAsString();
                                        Producto ResultadoUpdate = objectMapper.readValue(JsonResultadoGet,
                                                        Producto.class);
                                        assertEquals(ResultadoUpdate.getPrecio(), productoActualizado.getPrecio());
                                }).andDo(print());

        }

        @Test
        void testUpdateResgisterNotExists() throws Exception {

                Producto producto = new Producto();
                producto.setNombre("Arroz");
                producto.setMarca("Alguna Marca");
                producto.setPrecio(2.2);

                String ProductoJson = objectMapper.writeValueAsString(producto);

                String JsonResultadoPost = mockMvc.perform(post("/producto")
                                .contentType("application/json")
                                .content(ProductoJson))
                                .andExpect(status().isOk())
                                .andDo(print()).andReturn().getResponse().getContentAsString();

                Producto productoActualizado = objectMapper.readValue(JsonResultadoPost, Producto.class);
                String JSonProductoActualizado = objectMapper.writeValueAsString(productoActualizado);

                mockMvc.perform(delete("/producto/" + productoActualizado.getId()))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                mockMvc.perform(put("/producto")
                                .contentType("application/json")
                                .content(JSonProductoActualizado))
                                .andExpect(status().is4xxClientError())
                                .andDo(print());

        }
}
