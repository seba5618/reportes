package ar.com.bambu.models.impl;

import ar.com.bambu.models.Model;

import java.io.Serializable;
import java.util.List;

public class FacturaElectronica implements Serializable, Model<Void, FacturaDetalle, Void> {

    List<FacturaDetalle> detalle;

    @Override
    public Void getCabecera() {
        return null;
    }

    @Override
    public List<FacturaDetalle> getDetalle() {
        return null;
    }

    @Override
    public Void getPie() {
        return null;
    }

    public void setDetalle(List<FacturaDetalle> detalle) {
        this.detalle = detalle;
    }
}
