package cic25.proyPareja002.grupo8.app.uc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
public class ProverdorDeProducto {

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

        mockMvc.perform(post("/producto/provedor")
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

}