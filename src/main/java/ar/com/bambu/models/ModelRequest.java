package ar.com.bambu.models;

import ar.com.bambu.entities.Eventos;

public interface ModelRequest {
    Eventos getEvento();

    String getCodigoBarraFactura();

    String getCAE();

    String getVencimientoCAE();
}
