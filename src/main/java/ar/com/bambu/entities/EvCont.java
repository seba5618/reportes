package ar.com.bambu.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(EvContId.class)
public class EvCont {

    @Id
    private int posicion;
    @Id
    private int cajaZ;
    @Id
    private long idEvento;
    private int cantidad;
    private double total;
    private int codArticulo;



    @Transient
    private String articuloName;

    public EvCont(EvCont ev, String articuloName) {
        this.posicion=ev.posicion;
        this.cajaZ=ev.cajaZ;
        this.idEvento=ev.idEvento;
        this.cantidad=ev.cantidad;
        this.total=ev.total;
        this.codArticulo=ev.codArticulo;
        this.articuloName = articuloName;
    }

    public int getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getArticuloName() {
        return articuloName;
    }

    public void setArticuloName(String articuloName) {
        this.articuloName = articuloName;
    }

    public EvCont() {
    }

    public EvCont(int posicion, int cajaZ, long idEvento) {
        this.posicion = posicion;
        this.cajaZ = cajaZ;
        this.idEvento = idEvento;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getCajaZ() {
        return cajaZ;
    }

    public void setCajaZ(int cajaZ) {
        this.cajaZ = cajaZ;
    }

    public long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Double getPrecioUnitario() {
        return (total / cantidad) ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EvCont)) return false;
        EvCont evCont = (EvCont) o;
        return posicion == evCont.posicion &&
                cajaZ == evCont.cajaZ &&
                idEvento == evCont.idEvento;
    }

    @Override
    public String toString() {
        return "EvCont{" +
                "posicion=" + posicion +
                ", cajaZ=" + cajaZ +
                ", idEvento=" + idEvento +
                ", cantidad=" + cantidad +
                ", total=" + total +
                ", articuloName=" + articuloName +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(posicion, cajaZ, idEvento);
    }
}
