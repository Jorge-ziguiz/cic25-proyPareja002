package cic25.proyPareja002.grupo8.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
    @OneToOne(mappedBy = "marca",cascade = {})
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Id == null) ? 0 : Id.hashCode());
        result = prime * result + ((NombreComercial == null) ? 0 : NombreComercial.hashCode());
        result = prime * result + ((LugarDeOrigen == null) ? 0 : LugarDeOrigen.hashCode());
        result = prime * result + ((producto == null) ? 0 : producto.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Marca other = (Marca) obj;
        if (Id == null) {
            if (other.Id != null)
                return false;
        } else if (!Id.equals(other.Id))
            return false;
        if (NombreComercial == null) {
            if (other.NombreComercial != null)
                return false;
        } else if (!NombreComercial.equals(other.NombreComercial))
            return false;
        if (LugarDeOrigen == null) {
            if (other.LugarDeOrigen != null)
                return false;
        } else if (!LugarDeOrigen.equals(other.LugarDeOrigen))
            return false;
        if (producto == null) {
            if (other.producto != null)
                return false;
        } else if (!producto.equals(other.producto))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Marca [Id=" + Id + ", NombreComercial=" + NombreComercial + ", LugarDeOrigen=" + LugarDeOrigen + "]";
    }

}
