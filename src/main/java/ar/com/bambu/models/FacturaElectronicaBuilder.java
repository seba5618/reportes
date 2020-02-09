package ar.com.bambu.models;

import ar.com.bambu.entities.*;
import ar.com.bambu.models.impl.FacturaDetalle;
import ar.com.bambu.models.impl.FacturaElectronica;

import java.io.IOException;
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
    TpvConfig tpvConfig;
    Cajeros cajeros;


    public FacturaElectronicaBuilder withEvento(Eventos ev) {
        this.cabecera = ev;
        return this;
    }

    public FacturaElectronicaBuilder withDetalle(List<EvCont> detalle) {
        Stream<EvCont> sorted = detalle.stream().sorted((o1, o2) -> o2.getCantidad().compareTo(o1.getCantidad()));
        List<EvCont> filteredDetalle = new ArrayList<>();

        System.out.println("***** filtrar anulados 1 ****");
        sorted.forEach(evCont -> this.filtrarAnulados(filteredDetalle, evCont));

        this.detalle = filteredDetalle.stream().filter( evCont -> evCont.getCantidad()>0).collect(Collectors.toList());
        Stream<EvCont> sortedByPosicion = this.detalle.stream().sorted((o1, o2) -> Integer.compare(o1.getPosicion(),o2.getPosicion()));

        this.detalle =sortedByPosicion.collect(Collectors.toList());
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

    public FacturaElectronicaBuilder withTpvconfig(TpvConfig cl) {
        this.tpvConfig = cl;
        return this;
    }

    public FacturaElectronicaBuilder withCajeros(Cajeros cl) {
        this.cajeros= cl;
        return this;
    }

    public FacturaElectronica build() {
        FacturaElectronica result = new FacturaElectronica();

        detalle.forEach(c -> {
            FacturaDetalle detalle = new FacturaDetalle();
            detalle.setTipoComprobante(this.cabecera.getTipoEvento());
            detalle.setNumeroYTipoComprobante(this.cabecera.getCaja(), this.cabecera.getNroTicket(),this.cabecera.getTipoEvento());
            detalle.setFechaVigencia(this.cabecera.getDosificacionOrden());
            detalle.setDetalleFactura(c, this.cabecera.getTipoEvento());
            detalle.setDataCliente(this.clientes);
            detalle.setCondicionVenta(pie);
            detalle.setDataPieMontoPromociones(this.detalle, this.cabecera.getTipoEvento());
            detalle.setFechaWithFechaInvel(cabecera);
            detalle.setPathLogo(this.tpvConfig.getSucursal());
            detalle.setNUMVENDDOR(this.cabecera.getNroVendedor1());
            if(this.cajeros != null)
                detalle.setNOMVENDEDOR(" " + this.cajeros.getNombreCajero());
            else
                detalle.setNOMVENDEDOR(" ");

            //usaremos esto para las observaciones
            try {
                detalle.setF2_XOBS();
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.addDetalle(detalle);
        });

        return result;
    }

    /**
     * Asume la lista esta ordenada de cantidad mayor a menor y devuelve otra lista sin los eventos de anulacion y con
     * las ventas anuladas en cantidad zero.
     * @param source
     * @param newElem
     * @return
     */
    private List<EvCont> filtrarAnulados(List<EvCont> source, EvCont newElem) {


               Optional<EvCont> articuloEnTicketOptional = source.stream().filter(evCont -> evCont.getCodArticulo() == newElem.getCodArticulo()
                && evCont.getOrigen() == newElem.getOrigen()&& Math.abs(Math.abs(evCont.getPrecioUnitarioSinIva())- Math.abs(newElem.getPrecioUnitarioSinIva()) ) < 0.006 ).findAny();

        if (articuloEnTicketOptional.isPresent()) {
            EvCont articuloEnTicket = articuloEnTicketOptional.get();

            System.out.println("Antes " + articuloEnTicket.getCantidad() +  "  Art " + articuloEnTicket.getCodArticulo()+ "Sumo "+ newElem.getCantidad() + " Posi " + newElem.getPosicion());
            articuloEnTicket.setCantidad(articuloEnTicket.getCantidad() + newElem.getCantidad());
            System.out.println("Despues " + articuloEnTicket.getCantidad() +  "  Art " + articuloEnTicket.getCodArticulo());
            System.out.println("Precios " + Math.abs(articuloEnTicket.getPrecioUnitarioSinIva()) +  "  nuevo " + Math.abs(newElem.getPrecioUnitarioSinIva()));

        } else {
      
                source.add(newElem);
            
        }
        return source;
    }
}
