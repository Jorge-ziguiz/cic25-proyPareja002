package cic25.proyPareja002.grupo8.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cic25.proyPareja002.grupo8.app.model.Marca;
import cic25.proyPareja002.grupo8.app.model.Producto;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,Long> {

}
