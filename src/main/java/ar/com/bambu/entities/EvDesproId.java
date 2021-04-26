package ar.com.bambu.entities;

import java.io.Serializable;
import java.util.Objects;

public class EvDesproId implements Serializable {

        private int posicion;
        private long cajaZ;
        private long idEvento;


        public EvDesproId() {
        }

        public EvDesproId(int posicion, long cajaZ, long idEvento) {
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
            if (!(o instanceof ar.com.bambu.entities.EvDesproId)) return false;
            ar.com.bambu.entities.EvDesproId evDesproId = (ar.com.bambu.entities.EvDesproId) o;
            return posicion == evDesproId.posicion &&
                    cajaZ == evDesproId.cajaZ &&
                    idEvento == evDesproId.idEvento;
        }

        @Override
        public int hashCode() {
            return Objects.hash(posicion, cajaZ, idEvento);
        }
    }


