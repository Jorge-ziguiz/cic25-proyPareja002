package cic25.proyPareja002.grupo8.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cic25.proyPareja002.grupo8.app.exeptions.SecureNoAllowNewID;
import cic25.proyPareja002.grupo8.app.model.Producto;
import cic25.proyPareja002.grupo8.app.service.ProductoService;

@RestController
@RequestMapping(path = "producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @PostMapping()
    public Producto create(@RequestBody Producto producto) {

        if (producto.getId() != null && producto.getId() != 0) {
            throw new SecureNoAllowNewID("no se puede crear un producto con ID");
        }
        producto.setMarca(null);
        return productoService.create(producto);
    }

    @PostMapping("marca")
    public Producto productoProvedor(@RequestBody Producto producto) {
        if (producto.getId() != null && producto.getId() != 0) {
            throw new SecureNoAllowNewID("no se puede crear un producto con ID");
        }
        
        return productoService.create(producto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        productoService.delete(Long.valueOf(id));
    }

    @PutMapping()
    public void update(@RequestBody Producto producto) {
        if (getById(producto.getId()) == null || getById(producto.getId()).getId() == 0) {
            throw new SecureNoAllowNewID("no se pude actualizar un registro que no exsite");
        }
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
