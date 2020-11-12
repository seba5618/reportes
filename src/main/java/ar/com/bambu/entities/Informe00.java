package ar.com.bambu.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "informe_00")
public class Informe00 implements Serializable {

    @Id
    private int id;

    private String variable;
    private Double importe;
    private Double cantidad;
    private String tipo;
    private Long codigo;
    @ManyToOne ()
    @JoinColumn(name = "cajero")
    private Cajeros cajero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Cajeros getCajero() {
        return cajero;
    }

    public void setCajero(Cajeros cajero) {
        this.cajero = cajero;
    }
}
