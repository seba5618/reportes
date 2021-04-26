package ar.com.bambu.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(EvDesproId.class)
public class EvDespro {

    @Id
    private int posicion;

    @Id
    @Column(name = "CAJA_Z")
    private long cajaZ;

    @Id
    private long idEvento;
    private Double importe;
    private double iva;
    private int codArticulo;

    public EvDespro() {
    }
    public EvDespro(EvDespro ev) {

        this.posicion = ev.posicion;
        this.cajaZ = ev.cajaZ;
        this.idEvento = ev.idEvento;
        this.importe = ev.importe;
        this.iva = ev.iva;
        this.codArticulo = ev.codArticulo;

    }
    @Override
    public String toString() {
        return "EvDespro{" +
                "posicion=" + posicion +
                ", cajaZ=" + cajaZ +
                ", idEvento=" + idEvento +
                   '}';
    }


}
