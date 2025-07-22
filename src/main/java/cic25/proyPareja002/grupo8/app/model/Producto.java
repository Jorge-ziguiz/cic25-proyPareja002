package cic25.proyPareja002.grupo8.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    String Nombre;

    String Marca;

    double Precio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
        result = prime * result + ((Marca == null) ? 0 : Marca.hashCode());
        long temp;
        temp = Double.doubleToLongBits(Precio);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Producto other = (Producto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (Nombre == null) {
            if (other.Nombre != null)
                return false;
        } else if (!Nombre.equals(other.Nombre))
            return false;
        if (Marca == null) {
            if (other.Marca != null)
                return false;
        } else if (!Marca.equals(other.Marca))
            return false;
        if (Double.doubleToLongBits(Precio) != Double.doubleToLongBits(other.Precio))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", Nombre=" + Nombre + ", Marca=" + Marca + ", Precio=" + Precio + "]";
    }

    
    
}
