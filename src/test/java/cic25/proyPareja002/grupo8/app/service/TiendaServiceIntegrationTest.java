package cic25.proyPareja002.grupo8.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cic25.proyPareja002.grupo8.app.exeptions.TiendaNoExistia;
import cic25.proyPareja002.grupo8.app.exeptions.TiendaNulaException;
import cic25.proyPareja002.grupo8.app.model.Tienda;
import cic25.proyPareja002.grupo8.app.repository.TiendaRepository;

@SpringBootTest
public class TiendaServiceIntegrationTest {

    @Autowired
    TiendaService tiendaService;

    @Autowired
    TiendaRepository tiendaRepository;

    @AfterEach
    void limpiarBaseDeDatos() {
        tiendaRepository.deleteAll();
    }

    @Test
    void testDelete() throws TiendaNulaException {
        Tienda tienda = new Tienda();
        tienda.setNombre("nueva tienda");

        Tienda tiendaGuardada = tiendaService.post(tienda);
        Long id = tiendaGuardada.getId();

        assertEquals(tiendaGuardada.getNombre(), tiendaService.get(id).get().getNombre());

        tiendaService.delete(tiendaGuardada.getId());

        assertThrows(TiendaNulaException.class, () -> {
            tiendaService.get(id);
        });
    }

    @Test
    void testGet() {
        Tienda tienda = new Tienda();
        tienda.setNombre("nueva tienda");
        Tienda tiendaGuardada = tiendaService.post(tienda);

        Optional<Tienda> tiendaRecuperada = tiendaService.get(tiendaGuardada.getId());

        assertEquals(tiendaGuardada.getNombre(), ((Tienda) tiendaRecuperada.get()).getNombre());

    }

    @Test
    void testGetAll() {
        Tienda tienda = new Tienda();
        tienda.setNombre("nueva tienda");
        tienda.setUbicacion("Dirección de ejemplo 1");
        Tienda tiendaGuardada = tiendaService.post(tienda);

        Tienda tienda2 = new Tienda();
        tienda2.setNombre("nueva tienda 2");
        tienda2.setUbicacion("Dirección de ejemplo 2");
        Tienda tiendaGuardada2 = tiendaService.post(tienda2);

        Tienda tienda3 = new Tienda();
        tienda3.setNombre("nueva tienda 3");
        tienda3.setUbicacion("Dirección de ejemplo 3");
        Tienda tiendaGuardada3 = tiendaService.post(tienda3);

        List<Tienda> tiendasIntroducidas = new ArrayList<>();
        tiendasIntroducidas.add(tiendaGuardada);
        tiendasIntroducidas.add(tiendaGuardada2);
        tiendasIntroducidas.add(tiendaGuardada3);

        List<Tienda> tiendas = tiendaService.getAll();

        assertEquals(tiendasIntroducidas, tiendas);

        for (int i = 0; i < tiendas.size(); i++) {
            assertEquals(tiendasIntroducidas.get(i).getNombre(), tiendas.get(i).getNombre());
            assertEquals(tiendasIntroducidas.get(i).getUbicacion(), tiendas.get(i).getUbicacion());
        }

        assertEquals(tiendasIntroducidas.size(), tiendas.size());
    }

    @Test
    void testPost() {
        Tienda tienda = new Tienda();
        tienda.setNombre("carrefour");
        tienda.setUbicacion("El Alisal");

        Tienda tiendaResultado = tiendaService.post(tienda);

        assertEquals(tienda.getNombre(), tiendaResultado.getNombre());
    }

    @Test
    void testUpdate() {
        Tienda tienda = new Tienda();
        tienda.setNombre("Nueva tienda");
        tienda.setUbicacion("Dirección");
        Tienda tiendaGuardada = tiendaService.post(tienda);

        assertEquals(tienda.getNombre(), tiendaGuardada.getNombre());

        Tienda tiendaActualizada = new Tienda();
        tiendaActualizada.setNombre("Tienda con nombre cambiado");
        tiendaActualizada.setUbicacion("Dirección cambiada");
        tiendaActualizada.setId(tienda.getId());

        tiendaService.update(tienda.getId(), tiendaActualizada);

        assertEquals(tiendaGuardada, tiendaActualizada);
    }

    @Test
    void testUpdateTiendaNoExistente() throws Exception {
        Tienda tienda = new Tienda();
        tienda.setNombre("Nueva tienda");
        tienda.setUbicacion("Dirección");
        tiendaService.post(tienda);

        // Guardamos el id de la tienda
        Long idTienda = tienda.getId();

        // Comprobamos que la tienda se haya guardado correctamente
        assertEquals("Nueva tienda", tienda.getNombre());

        // Eliminamos la tienda, para que ya no exista ningún registro con el id de la
        // misma
        tiendaService.delete(idTienda);

        // Creamos una nueva tienda, a la cual le vamos a dar el id de la tienda borrada
        Tienda tienda2 = new Tienda();
        tienda2.setNombre("Nueva tienda");
        tienda2.setUbicacion("Dirección");
        tienda2.setId(idTienda);

        //Al tratar de actualizar con los datos de la nueva tienda, debería dar error, pues en la BD no hay ninguna tienda con ese ID (no sería una actualización)
        assertThrows(TiendaNoExistia.class, () -> {
            tiendaService.update(idTienda, tienda2);
        });
    }
}

