package ar.com.bambu.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Cajeros implements Serializable {
    private Short status;
    @Id
    private Integer codCajero;
    private String nombreCajero;


    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
    public Cajeros(Cajeros cl) {
        this.status = cl.status;
        this.codCajero = cl.codCajero;
        this.nombreCajero = cl.nombreCajero;

    }
    public Cajeros(Integer codCajero) {
        this.codCajero = codCajero;
    }

    public Cajeros(String nombreCajero) {
        this.nombreCajero = nombreCajero;
    }

    public Cajeros() {
    }

    public String getNombreCajero() {
        if(nombreCajero != null && !nombreCajero.isEmpty()) {
            return nombreCajero;
        } else
            return "";

    }

    public void setNombreCajero(String nombreCajero) {
        this.nombreCajero = nombreCajero;
    }
    public Integer getCodCajero() {
        return codCajero;
    }

    public void setCodCajero(Integer codCajero) {
        this.codCajero = codCajero;
    }

    @Override
    public int hashCode() {
        return Objects.hash( nombreCajero);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
