package ar.com.bambu.models.impl;

import ar.com.bambu.models.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FacturaElectronica implements Serializable, Model<Void, FacturaDetalle, Void> {

    List<FacturaDetalle> detalle = new ArrayList<>();

    @Override
    public Void getCabecera() {
        return null;
    }

    @Override
    public List<FacturaDetalle> getDetalle() {
        return detalle;
    }

    @Override
    public Void getPie() {
        return null;
    }

    public void setDetalle(List<FacturaDetalle> detalle) {
        this.detalle = detalle;
    }

    public void addDetalle(FacturaDetalle detalle){
        this.detalle.add(detalle);
    }


}
