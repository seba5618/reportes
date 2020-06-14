package ar.com.bambu.models;

import ar.com.bambu.entities.Eventos;



public class FacturaElectronicaRequest implements ModelRequest{

    private Eventos evento;
    private String codigoBarraFactura;
    private String cae;
    private String vencimientoCAE;


    @Override
    public Eventos getEvento() {
        return evento;
    }

    @Override
    public String getCodigoBarraFactura() {
        return codigoBarraFactura;
    }

    @Override
    public String getCAE() {
        return cae;
    }

    @Override
    public String getVencimientoCAE() {
        return vencimientoCAE;
    }

    public void setEvento(Eventos evento) {
        this.evento = evento;
    }

    public void setCodigoBarraFactura(String codigoBarraFactura) {
        this.codigoBarraFactura = codigoBarraFactura;
    }

    public void setCae(String cae) {
        this.cae = cae;
    }

    public void setVencimientoCAE(String vencimientoCAE) {
        this.vencimientoCAE = vencimientoCAE;
    }
}
