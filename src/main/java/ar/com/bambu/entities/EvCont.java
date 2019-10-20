package ar.com.bambu.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(EvContId.class)
public class EvCont {

    private static final int PROMOCION_PRE_PAGO = 4;
    private static final int PROMOCION_POST_PAGO = 5;

    @Id
    private int posicion;
    @Id
    private int cajaZ;
    @Id
    private long idEvento;
    private Double cantidad;
    private Integer origen;
    private double total;
    private int codArticulo;

    private Double importeSinIva;
    private Double impInt;
    private Double IVA1;


    @Transient
    private String articuloName;
    @Transient
    private String unidadDeMedida;

    public EvCont(EvCont ev, Articulo art) {
        this.posicion = ev.posicion;
        this.importeSinIva = ev.importeSinIva;
        this.impInt = ev.impInt;
        this.IVA1 = ev.IVA1;
        this.cajaZ = ev.cajaZ;
        this.idEvento = ev.idEvento;
        this.cantidad = ev.cantidad;
        this.total = ev.total;
        this.codArticulo = ev.codArticulo;
        this.articuloName = art.getNombre();
        this.unidadDeMedida = art.getUnidad();
        this.origen = ev.origen;
    }

    public Double getMontoPromocion() {
        Double result = 0d;
        if (this.origen == PROMOCION_PRE_PAGO || this.origen == PROMOCION_POST_PAGO) {
            result = total;
        }
        return result;
    }

    public Double getImporteSinIVA() {
        return importeSinIva;
    }

    public void setImporteSinIVA(Double importeSinIVA) {
        this.importeSinIva = importeSinIVA;
    }

    public Double getImpInt() {
        return impInt;
    }

    public void setImpInt(Double impInt) {
        this.impInt = impInt;
    }

    public Double getIVA1() {
        return IVA1;
    }

    public void setIVA1(Double IVA1) {
        this.IVA1 = IVA1;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public int getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getArticuloName() {
        return articuloName;
    }

    public void setArticuloName(String articuloName) {
        this.articuloName = articuloName;
    }

    public EvCont() {
    }

    public EvCont(int posicion, int cajaZ, long idEvento) {
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

    public int getCajaZ() {
        return cajaZ;
    }

    public void setCajaZ(int cajaZ) {
        this.cajaZ = cajaZ;
    }

    public long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Double getPrecioUnitarioSinIva() {
        return this.importeSinIva+this.impInt;
    }

    public Double getPrecioUnitarioConIva() {
        return this.importeSinIva+this.IVA1+this.impInt;
    }

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EvCont)) return false;
        EvCont evCont = (EvCont) o;
        return posicion == evCont.posicion &&
                cajaZ == evCont.cajaZ &&
                idEvento == evCont.idEvento;
    }

    @Override
    public String toString() {
        return "EvCont{" +
                "posicion=" + posicion +
                ", cajaZ=" + cajaZ +
                ", idEvento=" + idEvento +
                ", cantidad=" + cantidad +
                ", total=" + total +
                ", articuloName=" + articuloName +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(posicion, cajaZ, idEvento);
    }
}
