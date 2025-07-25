package cic25.proyPareja002.grupo8.app.uc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cic25.proyPareja002.grupo8.app.model.Administrador;
import cic25.proyPareja002.grupo8.app.model.Producto;
import cic25.proyPareja002.grupo8.app.model.Tienda;

@SpringBootTest
@AutoConfigureMockMvc
public class AdministracionDeTienda {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAdministrarTienda() throws Exception {
        Administrador admin = new Administrador();

        admin.setNombre("Carlos Mendoza");
        admin.setUsuario("carlosm");
        admin.setVersion(1L);

        Tienda tienda = new Tienda();

        tienda.setNombre("Supermercado Central");
        tienda.setUbicacion("Calle Falsa 123");
        List<Producto> productos = new ArrayList<>();
        tienda.setProductos(productos);

        // Asignar la tienda al administrador, y el administrador a la tienda
        admin.setTienda(tienda);
        tienda.setAdministrador(admin);

        // Convertimos el objeto del tipo marcado con @JsonIgnore (administrador), pues
        // la el administrador contenida dentro de su tienda será ignorado
        String adminJson = objectMapper.writeValueAsString(admin);

        // Con MockMvc simulamos la petición HTTP para crear una tienda
        mockMvc.perform(post("/tiendas/administrador")
                .contentType("application/json")
                .content(adminJson))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Tienda tiendaResultado = objectMapper.readValue(
                            result.getResponse().getContentAsString(), Tienda.class);
                    assertNotNull(tiendaResultado, "El administrador debe ser devuelto");
                });
    }
}
