package ar.com.bambu.entities;

import java.io.Serializable;
import java.util.Objects;

public class EvContId implements Serializable {

    private int posicion;
    private long cajaZ;
    private long idEvento;

    public EvContId() {
    }

    public EvContId(int posicion, long cajaZ, long idEvento) {
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

    public long getCajaZ() {
        return cajaZ;
    }

    public void setCajaZ(long cajaZ) {
        this.cajaZ = cajaZ;
    }

    public long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EvContId)) return false;
        EvContId evContId = (EvContId) o;
        return posicion == evContId.posicion &&
                cajaZ == evContId.cajaZ &&
                idEvento == evContId.idEvento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posicion, cajaZ, idEvento);
    }
}
