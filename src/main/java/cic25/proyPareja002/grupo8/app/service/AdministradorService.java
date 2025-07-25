package cic25.proyPareja002.grupo8.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cic25.proyPareja002.grupo8.app.exeptions.AdministradorNoExistente;
import cic25.proyPareja002.grupo8.app.model.Administrador;
import cic25.proyPareja002.grupo8.app.repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdministradorService.class);

    public List<Administrador> getAllAdministradores() {
        LOGGER.info("Informando sobre todos los administradores existentes en la BBDD");
        return administradorRepository.findAll();
    }

    public Optional<Administrador> get(Long id) {
        LOGGER.info(String.format("Obteniendo un administrador con el id %d", id));
        return administradorRepository.findById(id);
    }

    public Administrador create(Administrador administrador) {
        LOGGER.info(String.format("Creando un administrador"));
        return administradorRepository.save(administrador);
    }

    public Administrador update(Long id, Administrador administradorActualizado) {
        if (administradorRepository.findById(id).isEmpty()) {
            LOGGER.error(String.format("Error por intentar actualizar el administrador con el id %d", id));
            throw new AdministradorNoExistente("No se puede actualizar un administrador que no existía previamente");
        }
        administradorActualizado.setId(id);
        return administradorRepository.save(administradorActualizado);
    }

    public void delete(Long id) {
        LOGGER.info("Eliminando el administrador con id %d", id);
        administradorRepository.deleteById(id);
    }
}
