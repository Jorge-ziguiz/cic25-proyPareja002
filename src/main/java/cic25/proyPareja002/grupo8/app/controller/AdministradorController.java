package cic25.proyPareja002.grupo8.app.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cic25.proyPareja002.grupo8.app.exeptions.AdministradorNoExistente;
import cic25.proyPareja002.grupo8.app.model.Administrador;
import cic25.proyPareja002.grupo8.app.service.AdministradorService;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(AdministradorController.class);

    @GetMapping
    public List<Administrador> getAllAdministradores() {
        LOGGER.trace("Obteniendo todos los administradores en la ruta /administrador");
        return administradorService.getAllAdministradores();
    }

    @GetMapping("/{id}")
    public Optional<Administrador> get(@PathVariable Long id) {
        LOGGER.trace(String.format("Obteniendo el administrador de la ruta /administrador/%d", id));
        return administradorService.get(id);
    }

    @PostMapping
    public Administrador create(@RequestBody Administrador administrador) {
        LOGGER.trace(String.format("Creando un administrador mediante la ruta /administrador"));
        return administradorService.create(administrador);
    }

    @PutMapping("/{id}")
    public Administrador update(@PathVariable Long id, @RequestBody Administrador administradorActualizado) throws AdministradorNoExistente {
        LOGGER.trace("Actualizando el administrador de la ruta /administrador/%d", id);
        return administradorService.update(id, administradorActualizado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        LOGGER.trace("Ejecutando el borrado del administrador mediante la ruta /administrador/%d", id);
        administradorService.delete(id);
    }
}
