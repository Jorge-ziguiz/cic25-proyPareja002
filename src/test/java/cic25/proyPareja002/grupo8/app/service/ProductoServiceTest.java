package cic25.proyPareja002.grupo8.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cic25.proyPareja002.grupo8.app.model.Producto;

@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    ProductoService productoService;

    @Test
    void testCreate() {

        Producto producto = new Producto();

        producto.setNombre("Arroz");
        producto.setMarca("Alguna Marca");
        producto.setPrecio(2.2);

        Producto resultadoCreate = productoService.create(producto);
        assertTrue(resultadoCreate.getId() > 0);

    }

    @Test
    void testDelete() {
        Producto producto = new Producto();

        producto.setNombre("Arroz");
        producto.setMarca("Alguna Marca");
        producto.setPrecio(2.2);

        Producto resultadoCreate = productoService.create(producto);
        productoService.delete(resultadoCreate.getId());

        Producto getById = productoService.getById(producto.getId());

        assertTrue(getById == null);

    }

    @Test
    void testGetByAll() {
        Producto producto = new Producto();

        producto.setNombre("Arroz");
        producto.setMarca("Alguna Marca");
        producto.setPrecio(2.2);

        productoService.create(producto);

        List<Producto> productos = productoService.getByAll();

        assertTrue(!productos.isEmpty());

    }

    @Test
    void testGetById() {

        Producto producto = new Producto();

        producto.setNombre("Arroz");
        producto.setMarca("Alguna Marca");
        producto.setPrecio(2.2);

        Producto resultadoCreate = productoService.create(producto);
        Producto resultadoGetById = productoService.getById(resultadoCreate.getId());
        assertTrue(resultadoGetById.getId() > 0);

    }

    @Test
    void testUpdate() {

        Producto producto = new Producto();

        producto.setNombre("Arroz");
        producto.setMarca("Alguna Marca");

        Producto resultadoCreate = productoService.create(producto);

        producto.setPrecio(2.2);
        productoService.update(producto);

        Producto resultadoGetById = productoService.getById(resultadoCreate.getId());
        assertEquals(2.2, resultadoGetById.getPrecio());

    }
}
