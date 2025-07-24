package cic25.proyPareja002.grupo8.app.uc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import cic25.proyPareja002.grupo8.app.model.Marca;
import cic25.proyPareja002.grupo8.app.model.Producto;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureMockMvc
public class MarcaProvedorDeProducto {

        @Autowired
        MockMvc mockMvc;

        @Autowired
        ObjectMapper objectMapper;

        @Test
        void marcaProvedorDeProducto() throws Exception {

                Marca marca = new Marca();

                marca.setLugarDeOrigen("España");
                marca.setNombreComercial("marca de arroz");

                Producto producto = new Producto();
                producto.setNombre("Arroz");
                producto.setMarca(marca);
                producto.setPrecio(2.2);

                String ProductoJson = objectMapper.writeValueAsString(producto);

                mockMvc.perform(post("/producto/marca")
                                .contentType("application/json")
                                .content(ProductoJson))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(result -> {
                                        String JsonResultadoPost = result.getResponse().getContentAsString();
                                        Producto ProductoResultado = objectMapper.readValue(JsonResultadoPost,
                                                        Producto.class);
                                        assertTrue(ProductoResultado.getId() > 0);
                                        assertEquals(producto.getMarca().getLugarDeOrigen(), "España");
                                }).andDo(print());
        }

        @Test
        void UpdateInCascadeMarcaInProducto() throws Exception {
                Marca marca = new Marca();
                marca.setLugarDeOrigen("España");
                marca.setNombreComercial("marca de arroz");

                Producto producto = new Producto();
                producto.setNombre("Arroz");
                producto.setMarca(marca);
                producto.setPrecio(2.2);

                String ProductoJson = objectMapper.writeValueAsString(producto);

                String JsonProductoResultadoPost = mockMvc.perform(post("/producto/marca")
                                .contentType("application/json")
                                .content(ProductoJson))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Producto ResultadoProducto = objectMapper.readValue(JsonProductoResultadoPost, Producto.class);
                ResultadoProducto.getMarca().setLugarDeOrigen("Portugal");

                String ProductoMarcaActualizada = objectMapper.writeValueAsString(ResultadoProducto);

                mockMvc.perform(put("/producto")
                                .contentType("application/json")
                                .content(ProductoMarcaActualizada))
                                .andExpect(status().isOk())
                                .andDo(print());

                mockMvc.perform(get("/producto/" + ResultadoProducto.getId()))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(result -> {
                                        String JsonResultadoGet = result.getResponse().getContentAsString();
                                        Producto ResultadoUpdate = objectMapper.readValue(JsonResultadoGet,
                                                        Producto.class);
                                        assertEquals(ResultadoUpdate.getMarca().getLugarDeOrigen(), "Portugal");
                                }).andDo(print());
        }

        @Test
        void deleteInCascade() throws Exception {
                Marca marca = new Marca();

                marca.setLugarDeOrigen("España");
                marca.setNombreComercial("marca de arroz");

                Producto producto = new Producto();
                producto.setNombre("Arroz");
                producto.setMarca(marca);
                producto.setPrecio(2.2);

                String ProductoJson = objectMapper.writeValueAsString(producto);

                String JsonResultadoPost = mockMvc.perform(post("/producto/marca")
                                .contentType("application/json")
                                .content(ProductoJson))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andReturn().getResponse().getContentAsString();

                Producto ResultadoProducto = objectMapper.readValue(JsonResultadoPost, Producto.class);
                Marca MarcasAsosciadoAProdcuto = ResultadoProducto.getMarca();

                mockMvc.perform(delete("/producto/" + ResultadoProducto.getId()))
                                .andExpect(status().isOk())
                                .andDo(print());

                mockMvc.perform(get("/producto/" + ResultadoProducto.getId()))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(result -> {
                                        String JsonResultadoGet = result.getResponse().getContentAsString();
                                        assertEquals(JsonResultadoGet, "");
                                });

                mockMvc.perform(get("/marca/" + ResultadoProducto.getMarca().getId()))
                                .andExpect(status().isOk())
                                .andDo(print())
                                .andExpect(result -> {
                                        String JsonResultadoGet = result.getResponse().getContentAsString();
                                        Marca marcaSinProducto = objectMapper.readValue(JsonResultadoGet, Marca.class);
                                        
                                        assertTrue(marcaSinProducto.getProducto() == null);
                                        assertEquals(marcaSinProducto.getId(), MarcasAsosciadoAProdcuto.getId());
                                        
                                });
        }
}