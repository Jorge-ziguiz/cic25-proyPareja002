package cic25.proyPareja002.grupo8.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cic25.proyPareja002.grupo8.app.model.Producto;
import cic25.proyPareja002.grupo8.app.respository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto create(Producto producto) {
        return productoRepository.save(producto);
    }

    public void delete(Long id) {
        productoRepository.deleteById(Long.valueOf(id));
    }

    public void update(Producto producto) {
        if (getById(producto.getId()) != null || getById(producto.getId()).getId() != 0) {
            productoRepository.save(producto);
        }
    }

    public Producto getById(Long id) {
        Optional<Producto> resultado = productoRepository.findById(id);
        return resultado.orElse(null);
    }

    public List<Producto> getByAll() {
        List<Producto> resultado = productoRepository.findAll();
        return resultado;
    }
}
