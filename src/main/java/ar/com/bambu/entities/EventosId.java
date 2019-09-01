package ar.com.bambu.entities;

import java.io.Serializable;
import java.util.Objects;

public class EventosId implements Serializable {

    private long idEvento;
    private long cajaZ;

    public EventosId() {
    }

    public EventosId(long idEvento, long cajaZ) {
        this.idEvento = idEvento;
        this.cajaZ = cajaZ;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventosId)) return false;
        EventosId eventosId = (EventosId) o;
        return idEvento == eventosId.idEvento &&
                cajaZ == eventosId.cajaZ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, cajaZ);
    }
}
