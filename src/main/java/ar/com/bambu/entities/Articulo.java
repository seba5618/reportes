package ar.com.bambu.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(ArticuloId.class)
public class Articulo {

    @Id
    private int codInterno;

    private String nombre;
    private String unidad;
    private Integer codIva;
    @Id
    private String codigoBarra;




    public Articulo() {
    }

    public Articulo(int codInterno, String nombre) {
        this.codInterno = codInterno;
        this.nombre = nombre;
    }

    public Integer getCodIva() {
        return codIva;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public void setCodIva(Integer codIva) {
        this.codIva = codIva;
    }

    public int getCodInterno() {
        return codInterno;
    }

    public void setCodInterno(int codInterno) {
        this.codInterno = codInterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Articulo)) return false;
        Articulo articulo = (Articulo) o;
        return codInterno == articulo.codInterno &&
                Objects.equals(nombre, articulo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codInterno, nombre);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
