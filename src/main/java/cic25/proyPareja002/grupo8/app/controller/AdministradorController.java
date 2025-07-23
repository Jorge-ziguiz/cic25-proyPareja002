package cic25.proyPareja002.grupo8.app.controller;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public List<Administrador> getAllAdministradores() {
        return administradorService.getAllAdministradores();
    }

    @GetMapping("/{id}")
    public Optional<Administrador> get(@PathVariable Long id) {
        return administradorService.get(id);
    }

    @PostMapping
    public Administrador create(@RequestBody Administrador administrador) {
        return administradorService.create(administrador);
    }

    @PutMapping("/{id}")
    public Administrador update(@PathVariable Long id, @RequestBody Administrador administradorActualizado) throws AdministradorNoExistente {
        return administradorService.update(id, administradorActualizado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        administradorService.delete(id);
    }
}
