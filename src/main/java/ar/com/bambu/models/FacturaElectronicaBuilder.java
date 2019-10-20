package ar.com.bambu.models;

import ar.com.bambu.entities.Clientes;
import ar.com.bambu.entities.EvCont;
import ar.com.bambu.entities.EvMedios;
import ar.com.bambu.entities.Eventos;
import ar.com.bambu.models.impl.FacturaDetalle;
import ar.com.bambu.models.impl.FacturaElectronica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FacturaDetalle es un clase modelo peculiar, donde se devuelve una lista de detalles donde tambien esta repetido
 * en cada objeto la cabecera y el pie. Es un objeto externo al punto de venta y aca tomamos objetos de nuestra base
 * para obtener un objeto FacturaElectronica que es el datasource del jasper externo merft01
 */
public class FacturaElectronicaBuilder {
    Eventos cabecera;
    List<EvCont> detalle;
    EvMedios pie;
    Clientes clientes;


    public FacturaElectronicaBuilder withEvento(Eventos ev) {
        this.cabecera = ev;
        return this;
    }

    public FacturaElectronicaBuilder withDetalle(List<EvCont> detalle) {
        List<EvCont> filteredDetalle = new ArrayList<>();
        detalle.forEach(evCont -> this.filtrarAnulados(filteredDetalle, evCont));

        this.detalle = filteredDetalle.stream().filter( evCont -> evCont.getCantidad()>0).collect(Collectors.toList());
        return this;
    }

    public FacturaElectronicaBuilder withPie(EvMedios ev) {
        this.pie = ev;
        return this;
    }

    public FacturaElectronicaBuilder withCliente(Clientes cl) {
        this.clientes = cl;
        return this;
    }

    public FacturaElectronica build() {
        FacturaElectronica result = new FacturaElectronica();

        detalle.forEach(c -> {
            FacturaDetalle detalle = new FacturaDetalle();
            detalle.setTipoComprobante(this.cabecera.getTipoEvento());
            detalle.setNumeroYTipoComprobante(this.cabecera.getCaja(), this.cabecera.getNroTicket());
            detalle.setDetalleFactura(c, this.cabecera.getTipoEvento());
            detalle.setDataCliente(this.clientes);
            detalle.setCondicionVenta(pie);
            detalle.setDataPieMontoPromociones(this.detalle);
            result.addDetalle(detalle);
        });

        return result;
    }

    private List<EvCont> filtrarAnulados(List<EvCont> source, EvCont newElem) {

        Stream<EvCont> sorted = source.stream().sorted((o1, o2) -> o2.getCantidad().compareTo(o1.getCantidad()));

        Optional<EvCont> articuloEnTicketOptional = sorted.filter(evCont -> evCont.getCodArticulo() == newElem.getCodArticulo()
                && evCont.getOrigen() == newElem.getOrigen()).findAny();
        if (articuloEnTicketOptional.isPresent()) {
            EvCont articuloEnTicket = articuloEnTicketOptional.get();
            articuloEnTicket.setCantidad(articuloEnTicket.getCantidad() + newElem.getCantidad());

        } else {
            source.add(newElem);
        }
        return source;
    }
}
