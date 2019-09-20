package ar.com.bambu.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Medios {

    @Id
    private int codMedio;

    private String nombre;


    public Medios() {
    }

    public Medios(int codMedio, String nombre) {
        this.codMedio = codMedio;
        this.nombre = nombre;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medios)) return false;
        Medios articulo = (Medios) o;
        return codMedio == articulo.codMedio &&
                Objects.equals(nombre, articulo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codMedio, nombre);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
