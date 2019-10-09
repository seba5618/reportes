package ar.com.bambu.models.impl;
import ar.com.bambu.entities.EvCont;
import ar.com.bambu.entities.EvMedios;
import ar.com.bambu.entities.Eventos;
import ar.com.bambu.models.Model;

import java.io.Serializable;
import java.util.List;

public class Cotizacion implements Serializable, Model<Eventos, EvCont, EvMedios> {
    private Eventos cabecera;
    private List<EvCont> detalle;
    private EvMedios pie;

    public Eventos getCabecera() {
        return cabecera;
    }

    public void setCabecera(Eventos cabecera) {
        this.cabecera = cabecera;
    }

    public List<EvCont> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<EvCont> detalle) {
        this.detalle = detalle;
    }

    public EvMedios getPie() {
        return pie;
    }

    public void setPie(EvMedios pie) {
        this.pie = pie;
    }
}
