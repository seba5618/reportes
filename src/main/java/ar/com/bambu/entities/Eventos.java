package ar.com.bambu.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(EventosId.class)
public class Eventos implements Serializable {

    @Id
    private long idEvento;
    @Id
    private long cajaZ;
    private int tipoEvento;
    private long nroTicket;
    private double importeSinIva;
    private double impInt;
    private double exento;
    private double iva1;

    public Eventos() {
    }

    public long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }

    public long getCajaZ() {
        return cajaZ;
    }

    public void setCajaZ(long cajaZ) {
        this.cajaZ = cajaZ;
    }

    public int getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(int tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public long getNroTicket() {
        return nroTicket;
    }

    public void setNroTicket(long nroTicket) {
        this.nroTicket = nroTicket;
    }

    public double getImporteSinIva() {
        return importeSinIva;
    }

    public void setImporteSinIva(double importeSinIva) {
        this.importeSinIva = importeSinIva;
    }

    public double getImpInt() {
        return impInt;
    }

    public void setImpInt(double impInt) {
        this.impInt = impInt;
    }

    public double getExento() {
        return exento;
    }

    public void setExento(double exento) {
        this.exento = exento;
    }

    public double getIva1() {
        return iva1;
    }

    public void setIva1(double iva1) {
        this.iva1 = iva1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Eventos)) return false;
        Eventos eventos = (Eventos) o;
        return idEvento == eventos.idEvento &&
                cajaZ == eventos.cajaZ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, cajaZ);
    }
}
