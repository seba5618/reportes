package ar.com.bambu.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArticuloIva {
    private Integer status;
    @Id
    private int codIva;
    private Double porcIva1;
    private Double porcIva2;
    private String descIva;
    private Double tasaDgi;
    @Column(name = "PORC_212")
    private Double porc212;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getCodIva() {
        return codIva;
    }

    public void setCodIva(int codIva) {
        this.codIva = codIva;
    }

    public Double getPorcIva1() {
        return porcIva1;
    }

    public void setPorcIva1(Double porcIva1) {
        this.porcIva1 = porcIva1;
    }

    public Double getPorcIva2() {
        return porcIva2;
    }

    public void setPorcIva2(Double porcIva2) {
        this.porcIva2 = porcIva2;
    }

    public String getDescIva() {
        return descIva;
    }

    public void setDescIva(String descIva) {
        this.descIva = descIva;
    }

    public Double getTasaDgi() {
        return tasaDgi;
    }

    public void setTasaDgi(Double tasaDgi) {
        this.tasaDgi = tasaDgi;
    }

    public Double getPorc212() {
        return porc212;
    }

    public void setPorc212(Double porc212) {
        this.porc212 = porc212;
    }
}
