package ar.com.bambu.models.impl;
import ar.com.bambu.entities.EvCont;
import ar.com.bambu.entities.EvMedios;
import ar.com.bambu.entities.Eventos;
import ar.com.bambu.models.ModelResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cotizacion implements Serializable, ModelResponse<Eventos, EvCont, EvMedios> {
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
        Stream<EvCont> sorted = detalle.stream().sorted((o1, o2) -> o2.getCantidad().compareTo(o1.getCantidad()));
        List<EvCont> filteredDetalle = new ArrayList<>();
        sorted.forEach(evCont -> this.filtrarAnulados(filteredDetalle, evCont));

        this.detalle = filteredDetalle.stream().filter( evCont -> evCont.getCantidad()>0).collect(Collectors.toList());
        Stream<EvCont> sortedByPosicion = this.detalle.stream().sorted((o1, o2) -> Integer.compare(o1.getPosicion(),o2.getPosicion()));

        this.detalle =sortedByPosicion.collect(Collectors.toList());

    }

    public EvMedios getPie() {
        return pie;
    }

    public void setPie(EvMedios pie) {
        this.pie = pie;
    }

    private List<EvCont> filtrarAnulados(List<EvCont> source, EvCont newElem) {
        Optional<EvCont> articuloEnTicketOptional = source.stream().filter(evCont -> evCont.getCodArticulo() == newElem.getCodArticulo()
                && evCont.getOrigen() == newElem.getOrigen()&& Math.abs(evCont.getPrecioUnitario())== Math.abs(newElem.getPrecioUnitario())).findAny();
        if (articuloEnTicketOptional.isPresent()) {
            EvCont articuloEnTicket = articuloEnTicketOptional.get();
            articuloEnTicket.setCantidad(articuloEnTicket.getCantidad() + newElem.getCantidad());

        } else {
            source.add(newElem);
        }
        return source;
    }
}
