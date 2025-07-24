package cic25.proyPareja002.grupo8.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cic25.proyPareja002.grupo8.app.model.Producto;
import cic25.proyPareja002.grupo8.app.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductoService.class);

    public Producto create(Producto producto) {
        return productoRepository.save(producto);
    }

    public void delete(Long id) {
        productoRepository.deleteById(Long.valueOf(id));
    }

    public void update(Producto producto) {
        productoRepository.save(producto);
    }

    @Transactional(readOnly = true)
    public Producto getById(Long id) {
        Optional<Producto> resultado = productoRepository.findById(id);
        return resultado.orElse(null);
    }

    public List<Producto> getByAll() {
        List<Producto> resultado = productoRepository.findAll();
        return resultado;
    }
}
