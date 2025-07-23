package cic25.proyPareja002.grupo8.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cic25.proyPareja002.grupo8.app.exeptions.TiendaNoExistia;
import cic25.proyPareja002.grupo8.app.exeptions.TiendaNulaException;
import cic25.proyPareja002.grupo8.app.model.Tienda;
import cic25.proyPareja002.grupo8.app.repository.TiendaRepository;

@Service
public class TiendaService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TiendaService.class);

    @Autowired
    private TiendaRepository tiendaRepository;

    public List<Tienda> getAll() {
        LOGGER.info("Mostrando todas las tiendas de la BBDD");
        return tiendaRepository.findAll();
    }

    public Optional<Tienda> get(Long id) {
        LOGGER.info(String.format("Mostrando la tienda con id %d", id));
        Optional<Tienda> tiendaBuscada = tiendaRepository.findById(id);

        if (tiendaBuscada.isEmpty()) {
            throw new TiendaNulaException(String.format("No se ha encontrado ningún resultado para el id %d", id));
        }
        return tiendaBuscada;
    }

    public Tienda post(Tienda tienda) {
        LOGGER.info(String.format("Actualizando la tienda con id %d", tienda.getId()));
        Tienda tiendaResultado = tiendaRepository.save(tienda);
        return tiendaResultado;
    }

    public void delete(Long id) {
        LOGGER.info(String.format("Se ha eliminado la tienda con id %d", id));
        tiendaRepository.deleteById(id);
    }

    public Tienda update(Long id, Tienda tienda) throws TiendaNoExistia{
        if(tiendaRepository.findById(id).isEmpty()){
            throw new TiendaNoExistia();
        }

        tienda.setId(id);

        return tiendaRepository.save(tienda);
    }
}
 