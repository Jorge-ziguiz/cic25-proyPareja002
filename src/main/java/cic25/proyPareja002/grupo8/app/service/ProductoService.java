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
        LOGGER.info("creacion de un producto");
        return productoRepository.save(producto);
    }

    public void delete(Long id) {
        LOGGER.info("eliminacion de un registro de producto");
        Producto producto = getById(Long.valueOf(id));
        producto.getMarca().setProducto(null);
        productoRepository.deleteById(Long.valueOf(id));
    }

    public void update(Producto producto) {
        LOGGER.info("actualizacion de un producto");
        productoRepository.save(producto);
    }

    @Transactional(readOnly = true)
    public Producto getById(Long id) {
        LOGGER.info("obtener un registro de producto");
        Optional<Producto> resultado = productoRepository.findById(id);
        return resultado.orElse(null);
    }

    public List<Producto> getByAll() {
        LOGGER.info("obtener todos registros de producto");
        List<Producto> resultado = productoRepository.findAll();
        return resultado;
    }

    private boolean hasMarca(Optional<Producto> producto){
        boolean has;

        if(producto.isPresent()){
            has = producto.get().getMarca()!=null;
        }else{
            has = false;
        }
        return has;
    }

}
