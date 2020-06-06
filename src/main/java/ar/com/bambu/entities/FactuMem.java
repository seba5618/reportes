package ar.com.bambu.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
public class FactuMem {

    @Id
    private Integer id;

    private Integer tipoDato;
    private String valor;

    public FactuMem() {
    }

    public FactuMem(String valor) {
        this.valor = valor;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(Integer tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    @Override
    public String toString() {
        return super.toString();
    }

    public FactuMem(FactuMem factu, String valor){
        this.id = factu.id;
        this.tipoDato = factu.tipoDato;
        this.valor = valor;

    }

    @Override
    public int hashCode() {
        return Objects.hash( valor);
    }

}
