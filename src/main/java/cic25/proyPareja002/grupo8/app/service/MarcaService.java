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
        return marcaRepository.save(Marca);
    }

    public void delete(Long id) {
        marcaRepository.deleteById(Long.valueOf(id));
    }

    public void update(Marca Marca) {
        marcaRepository.save(Marca);
    }

    public Marca getById(Long id) {
        Optional<Marca> resultado = marcaRepository.findById(id);
        return resultado.orElse(null);
    }

    public List<Marca> getByAll() {
        List<Marca> resultado = marcaRepository.findAll();
        return resultado;
    }
}
