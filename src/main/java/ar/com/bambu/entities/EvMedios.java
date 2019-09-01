package ar.com.bambu.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(EvMediosId.class)
public class EvMedios implements Serializable {

    @Id
    private long idEvento;
    @Id
    private int cajaZ;
    @Id
    private int posicion;

    private int modoPago;
    private int codSubMedio;
    private double importe;

    public EvMedios() {
    }

    public long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }

    public int getCajaZ() {
        return cajaZ;
    }

    public void setCajaZ(int cajaZ) {
        this.cajaZ = cajaZ;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getModoPago() {
        return modoPago;
    }

    public void setModoPago(int modoPago) {
        this.modoPago = modoPago;
    }

    public int getCodSubMedio() {
        return codSubMedio;
    }

    public void setCodSubMedio(int codSubMedio) {
        this.codSubMedio = codSubMedio;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EvMedios)) return false;
        EvMedios evMedios = (EvMedios) o;
        return idEvento == evMedios.idEvento &&
                cajaZ == evMedios.cajaZ &&
                posicion == evMedios.posicion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, cajaZ, posicion);
    }
}
