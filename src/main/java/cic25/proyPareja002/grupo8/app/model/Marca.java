package cic25.proyPareja002.grupo8.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String NombreComercial;

    private String LugarDeOrigen;

    @JsonIgnore
    @OneToOne(mappedBy = "marcas")
    private Producto producto;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombreComercial() {
        return NombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        NombreComercial = nombreComercial;
    }

    public String getLugarDeOrigen() {
        return LugarDeOrigen;
    }

    public void setLugarDeOrigen(String lugarDeOrigen) {
        LugarDeOrigen = lugarDeOrigen;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
