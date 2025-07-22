package cic25.proyPareja002.grupo8.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cic25.proyPareja002.grupo8.app.model.Producto;
import cic25.proyPareja002.grupo8.app.service.ProductoService;

@RestController
@RequestMapping(path = "producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping()
    public Producto create(@RequestBody Producto producto) {

        if (producto.getId() != null) {
            throw new RuntimeException("hay que saltar una exepcion aqui pero antes crear una propia");
        }
        return productoService.create(producto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        productoService.delete(Long.valueOf(id));
    }

    @PutMapping()
    public void update(@RequestBody Producto producto) {
        productoService.update(producto);
    }

    @GetMapping("/{id}")
    public Producto getById(@PathVariable long id) {
        return productoService.getById(Long.valueOf(id));
    }

    @GetMapping()
    public List<Producto> getAll() {
        return productoService.getByAll();
    }

}
