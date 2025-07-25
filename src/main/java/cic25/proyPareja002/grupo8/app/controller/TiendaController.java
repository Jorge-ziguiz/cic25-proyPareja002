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

import cic25.proyPareja002.grupo8.app.exeptions.TiendaNoExistia;
import cic25.proyPareja002.grupo8.app.exeptions.TiendaNulaException;
import cic25.proyPareja002.grupo8.app.model.Administrador;
import cic25.proyPareja002.grupo8.app.model.Tienda;
import cic25.proyPareja002.grupo8.app.service.AdministradorService;
import cic25.proyPareja002.grupo8.app.service.TiendaService;

@RestController
@RequestMapping("/tiendas")
public class TiendaController {
    @Autowired
    TiendaService tiendaService;

    @Autowired
    AdministradorService administradorService;

    @GetMapping()
    public List<Tienda> getAll() {
        return tiendaService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Tienda> get(@PathVariable Long id) throws TiendaNulaException {
        return tiendaService.get(id);
    }

    @PostMapping()
    public Tienda post(@RequestBody Tienda tienda) {
        return tiendaService.post(tienda);
    }

    @PostMapping("/administrador")
    public Administrador crearAdministrador(Administrador administrador){
        return administradorService.create(administrador);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        tiendaService.delete(id);
    }

    @PutMapping("{id}")
    public Tienda update(@PathVariable Long id, @RequestBody Tienda nuevaTienda) throws TiendaNoExistia{
        nuevaTienda.setId(id);
        return tiendaService.update(nuevaTienda.getId(), nuevaTienda);
    }
}
