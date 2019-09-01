package ar.com.bambu.entities;

import java.io.Serializable;
import java.util.Objects;

public class EvMediosId implements Serializable {

    private long idEvento;
    private int cajaZ;
    private int posicion;

    public EvMediosId() {
    }

    public EvMediosId(long idEvento, int cajaZ, int posicion) {
        this.idEvento = idEvento;
        this.cajaZ = cajaZ;
        this.posicion = posicion;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EvMediosId)) return false;
        EvMediosId that = (EvMediosId) o;
        return idEvento == that.idEvento &&
                cajaZ == that.cajaZ &&
                posicion == that.posicion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, cajaZ, posicion);
    }
}
