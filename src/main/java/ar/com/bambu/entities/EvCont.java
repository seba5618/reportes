package ar.com.bambu.entities;



import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(EvContId.class)
public class EvCont {

    public static final Integer DESCUENTO_PRE_PAGO = 2;
    public static final Integer PROMOCION_PRE_PAGO = 4;
    public static final Integer PROMOCION_POST_PAGO = 5;
    public static final Integer ORIGEN_TICKET = 1;

    @Id
    private int posicion;
    @Id
    @Column(name = "CAJA_Z")
    private long cajaZ;
    @Id
    private long idEvento;
    private Double cantidad;
    private Integer origen;
    private double total;
    private int codArticulo;

    private Double importeSinIva;
    private Double impInt;
    private Double IVA1;


    private String nroVendedors = StringUtils.EMPTY;
    private int tipo3;


    @Transient
    private String articuloName;
    @Transient
    private String unidadDeMedida;
    @Transient
    private ArticuloIva articuloIVA;

    public EvCont() {
    }

    public EvCont(int posicion, int cajaZ, long idEvento) {
        this.posicion = posicion;
        this.cajaZ = cajaZ;
        this.idEvento = idEvento;
    }

    public EvCont(EvCont ev, Articulo art, ArticuloIva articuloIVA) {

        this.posicion = ev.posicion;
        this.importeSinIva = ev.importeSinIva;
        this.impInt = ev.impInt;
        this.IVA1 = ev.IVA1;
        this.cajaZ = ev.cajaZ;
        this.idEvento = ev.idEvento;
        this.cantidad = ev.cantidad;
        this.total = ev.total;
        this.codArticulo = ev.codArticulo;
        this.nroVendedors = ev.nroVendedors;
        this.tipo3 = ev.tipo3;
        this.articuloName = art.getNombre();
        this.unidadDeMedida = art.getUnidad();
        this.origen = ev.origen;
        this.articuloIVA = articuloIVA;
    }

    public Double getMontoPromocionSinIva() {
        Double result = 0d;
        if (this.isPromocion()) {
            result = this.getImporteSinIva() * this.getCantidad();
        }
        return result;
    }

    public Double getMontoPromocionConIva() {
        Double result = 0d;
        if (this.isPromocion()) {
            result = (this.getImporteSinIva() + this.getIVA1()) * cantidad;
        }
        return result;
    }

    public Double getMontoSinIVA(){
        Double result = 0d;

            result = this.getImporteSinIva() * this.getCantidad();

        return result;
    }

    public int getTipo3() {
        return tipo3;
    }

    public void setTipo3(int tipo3) {
        this.tipo3 = tipo3;
    }

    public String getNroVendedors() {
        return nroVendedors;
    }

    public void setNroVendedors(String nroVendedors) {
        this.nroVendedors = nroVendedors;
    }

    public Double getMontoConIVA(){
        Double result = 0d;

            result = (this.getImporteSinIva() + this.getIVA1()) * cantidad;

        return result;
    }

    public Double getMontoIVAComun(){
        Double result = 0d;
        if (this.articuloIVA.getCodIva() == 0){
            result += this.getIVA1() * this.getCantidad();
        }
        return result;
    }

    public Double getMontoIVAReducido(){
        Double result = 0d;
        if (this.articuloIVA.getCodIva() == 1){
            result += this.getIVA1() * this.getCantidad();
        }
        return result;
    }

    public Double getMontoExento(){
        Double result = 0d;
        if (this.articuloIVA.getCodIva() == 2){
            result +=  total;
        }
        return result;
    }

    public boolean isPromocion(){
        return PROMOCION_PRE_PAGO.equals(this.origen) || PROMOCION_POST_PAGO.equals(this.origen)
                || DESCUENTO_PRE_PAGO.equals(this.origen);
    }

    public boolean isAgrupable(){
        return StringUtils.isNotBlank(this.nroVendedors)
                && ((this.getTipo3() & 64) == 64 || (this.getTipo3() & 32) == 32)
                && EvCont.ORIGEN_TICKET.equals(this.getOrigen());
    }


    public Double getImporteSinIva() {
        return importeSinIva;
    }

    public void setImporteSinIva(Double importeSinIva) {
        this.importeSinIva = importeSinIva;
    }

    public ArticuloIva getArticuloIVA() {
        return articuloIVA;
    }

    public void setArticuloIVA(ArticuloIva articuloIVA) {
        this.articuloIVA = articuloIVA;
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

    public String getCodArticuloConcatOrigen(){
        return String.valueOf(this.getCodArticulo()).concat(String.valueOf(this.getOrigen()));
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

    public Double getPrecioUnitario() {
        return (this.total / this.cantidad) ;
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
