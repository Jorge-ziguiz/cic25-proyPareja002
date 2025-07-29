package cic25.proyPareja002.grupo8.app.service;

import java.util.Optional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cic25.proyPareja002.grupo8.app.model.Marca;
import cic25.proyPareja002.grupo8.app.repository.MarcaRepository;

@Service
@Transactional
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    private final static Logger LOGGER = LoggerFactory.getLogger(MarcaService.class);

    public Marca create(Marca Marca) {
        LOGGER.info("creacion del marca");
        return marcaRepository.save(Marca);
    }

    public void delete(Long id) {
        LOGGER.info("eliminado un registro de marca");
        marcaRepository.deleteById(Long.valueOf(id));
    }


    public void update(Marca Marca) {
        LOGGER.info("actualizando un registro de marca");
        marcaRepository.save(Marca);
    }

    public Marca getById(Long id) {
        LOGGER.info("obteniendo un registro de marca por id");
        Optional<Marca> resultado = marcaRepository.findById(id);
        return resultado.orElse(null);
    }

    public List<Marca> getByAll() {
        LOGGER.info("obteniendo todos registros de marca");
        List<Marca> resultado = marcaRepository.findAll();
        return resultado;
    }
}
