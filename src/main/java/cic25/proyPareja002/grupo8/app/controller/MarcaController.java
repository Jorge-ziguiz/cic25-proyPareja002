package cic25.proyPareja002.grupo8.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cic25.proyPareja002.grupo8.app.exeptions.SecureNoAllowNewID;
import cic25.proyPareja002.grupo8.app.model.Marca;
import cic25.proyPareja002.grupo8.app.service.MarcaService;

@RestController
@RequestMapping(path = "marca")
public class MarcaController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MarcaController.class);

    @Autowired
    private MarcaService MarcaService;

    @PostMapping()
    public Marca create(@RequestBody Marca Marca) {
        if (Marca.getId() != null && Marca.getId() != 0) {
            throw new SecureNoAllowNewID("no se puede crear un Marca con ID");
        }
        return MarcaService.create(Marca);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        MarcaService.delete(Long.valueOf(id));
    }

    @PutMapping()
    public void update(@RequestBody Marca Marca) {

        if (getById(Marca.getId()) == null || getById(Marca.getId()).getId() == 0) {
            throw new SecureNoAllowNewID("no se pude actualizar un registro que no exsite");
        }
        MarcaService.update(Marca);

    }

    @GetMapping("/{id}")
    public Marca getById(@PathVariable long id) {
        return MarcaService.getById(Long.valueOf(id));
    }

    @GetMapping()
    public List<Marca> getAll() {
        return MarcaService.getByAll();
    }

}
