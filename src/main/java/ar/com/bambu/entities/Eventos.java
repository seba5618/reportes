package ar.com.bambu.entities;

import javax.persistence.Column;
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
    @Column(name = "CAJA_Z")
    private long cajaZ;
    private int tipoEvento;
    private long nroTicket;
    private double importeSinIva;
    private double impInt;
    private double exento;
    private double iva1;
    private int codCliente;
    private int fecha;
    private int caja;


    private int hora;
    private int nroVendedor1;
    private int nroCotizacion;
    private int cajero;

    public Eventos() {
    }

    public int getCaja() {
        return caja;
    }

    public void setCaja(int caja) {
        this.caja = caja;
    }

    public double getTotalVenta() {
        return this.importeSinIva + this.impInt + this.exento + this.iva1;
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

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getNroVendedor1() {
        return nroVendedor1;
    }

    public void setNroVendedor1(int nroVendedor1) {
        this.nroVendedor1 = nroVendedor1;
    }

    public int getNroCotizacion() {
        return nroCotizacion;
    }

    public void setNroCotizacion(int nroCotizacion) {
        this.nroCotizacion = nroCotizacion;
    }

    public int getCajero() {
        return cajero;
    }

    public void setCajero(int cajero) {
        this.cajero = cajero;
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
    public String toString() {
        return "Eventos{" +
                "idEvento=" + idEvento +
                ", cajaZ=" + cajaZ +
                ", tipoEvento=" + tipoEvento +
                ", nroTicket=" + nroTicket +
                ", importeSinIva=" + importeSinIva +
                ", impInt=" + impInt +
                ", exento=" + exento +
                ", iva1=" + iva1 +
                ", codCliente=" + codCliente +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", nroVendedor1=" + nroVendedor1 +
                ", nroCotizacion=" + nroCotizacion +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, cajaZ);
    }
}
