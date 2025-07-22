package cic25.proyPareja002.grupo8.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cic25.proyPareja002.grupo8.app.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

}
