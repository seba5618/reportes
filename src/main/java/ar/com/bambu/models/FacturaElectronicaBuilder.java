package ar.com.bambu.models;

import ar.com.bambu.entities.*;
import ar.com.bambu.models.impl.FacturaDetalle;
import ar.com.bambu.models.impl.FacturaElectronica;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FacturaDetalle es un clase modelo peculiar, donde se devuelve una lista de detalles donde tambien esta repetido
 * en cada objeto la cabecera y el pie. Es un objeto externo al punto de venta y aca tomamos objetos de nuestra base
 * para obtener un objeto FacturaElectronica que es el datasource del jasper externo merft01
 */
public class FacturaElectronicaBuilder {

    private static final int TIPO_FACTURA_B = 17;
    private static final int TIPO_FACTURA_A = 16;
    private static final int COTIZACION= 92;
    private static final int REMITOS1= 11;
    private static final int ARTICULO_AGRUPADOR = 33;
    private final static Logger LOG = LoggerFactory.getLogger(FacturaElectronicaBuilder.class);

    FacturaElectronicaRequest request;
    Eventos cabecera;
    List<EvCont> detalle;
    EvMedios pie;
    Clientes clientes;
    TpvConfig tpvConfig;
    Cajeros cajeros;
    FactuMem factuMem;




    public FacturaElectronicaBuilder withRequest(FacturaElectronicaRequest req) {
        this.request = req;
        return this;
    }

    public FacturaElectronicaBuilder withEvento(Eventos ev){
        this.cabecera = ev;
        return this;
    }

    public FacturaElectronicaBuilder withDetalle(List<EvCont> detalle) {
        LOG.debug("***** filtrar anulados 1 ****");
        List<EvCont> evConts = this.agruparPorNroVendedorS(
                this.agruparMismosArticulosNeteadoDeAnulados(detalle)
        );
        Stream<EvCont> sortedByPosicion = evConts.stream().sorted(Comparator.comparingInt(EvCont::getPosicion));
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

    public FacturaElectronicaBuilder withFactuMem(FactuMem cl) {
        this.factuMem= cl;
        return this;
    }

    public FacturaElectronica build() {
        FacturaElectronica result = new FacturaElectronica();


        detalle.forEach(c -> {
            FacturaDetalle detalle = new FacturaDetalle();
            detalle.setTipoComprobante(this.cabecera.getTipoEvento());

            if( this.cabecera.getTipoEvento() == TIPO_FACTURA_A || this.cabecera.getTipoEvento() == TIPO_FACTURA_B
                    ||  this.cabecera.getTipoEvento() == Eventos.TIPO_NOTA_CREDITO_A || this.cabecera.getTipoEvento() == Eventos.TIPO_NOTA_CREDITO_B)
                detalle.setNumeroYTipoComprobante(this.cabecera.getSucComprobante(), this.cabecera.getNroComprobante(),this.cabecera.getTipoEvento());
            else
                detalle.setNumeroYTipoComprobante(this.cabecera.getCaja(), this.cabecera.getNroTicket(),this.cabecera.getTipoEvento());
            if(  this.cabecera.getTipoEvento() == Eventos.TIPO_NOTA_CREDITO_A || this.cabecera.getTipoEvento() == Eventos.TIPO_NOTA_CREDITO_B)
                detalle.setNumeroYTipoComprobanteAnt(this.cabecera.getSucComprobanteAnt(), this.cabecera.getNroComprobanteAnt(),this.cabecera.getTipoEvento());

            detalle.setFechaVigencia(this.cabecera.getDosificacionOrden());
            detalle.setDetalleFactura(c, this.cabecera.getTipoEvento());
            detalle.setDataCliente(this.clientes);
            detalle.setCondicionVenta(pie);
            detalle.setDataPieMontoPromociones(this.detalle, this.cabecera.getTipoEvento());
            detalle.setFechaWithFechaInvel(cabecera);
            detalle.setCAEData(this.request);
            detalle.setPathLogo(this.tpvConfig.getSucursal(), this.cabecera.getTipoEvento());
            detalle.setNUMVENDDOR(this.cabecera.getNroVendedor1());
            if(this.cajeros != null)
                detalle.setNOMVENDEDOR(" " + this.cajeros.getNombreCajero());
            else
                detalle.setNOMVENDEDOR("-");

            detalle.setCODCLIENTE(this.cabecera.getCodCliente());
            if(this.factuMem!=null) {
                detalle.setTELTRANS(" " + this.factuMem.getValor());
            }



            //usaremos esto para las observaciones
            try {
                detalle.setF2_XOBS(this.cabecera.getTipoEvento());
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.addDetalle(detalle);
        });

        return result;
    }



    /**
     * NroVendedorS se usa para agrupar articulos que deberian aparecer juntos en el ticket. Acumula montos en un unico
     * articulo acumulador (tipo3 = 33) todos los que en un mismo grupo sean tipo3 & 64 = 64
     * assumption: Hay solo un articulo acumulador en un mismo grupo.
     * @param source
     * @return
     */
    protected List<EvCont> agruparPorNroVendedorS(List<EvCont> source){
        List <EvCont> result = new ArrayList<>();
        Map<String, List<EvCont>> grouped = source.stream().collect(Collectors.groupingBy(EvCont::getNroVendedors));
        grouped.forEach((nroVendedor, evconts) -> {
            Optional<EvCont> acumulador = evconts.stream().filter(evCont -> ((evCont.getTipo3() & 32) == 32)).findFirst();
            List<EvCont> losPesables = evconts.stream().filter(evCont -> ((evCont.getTipo3() & 64) == 64)).collect(Collectors.toList());
            List<EvCont> losNoDeberiaAgrupar = evconts.stream().filter(evCont -> ((evCont.getTipo3() & 64) != 64 && (evCont.getTipo3() & 32) != 32)).collect(Collectors.toList());
            if ( acumulador.isPresent() && StringUtils.isNotEmpty(nroVendedor)){
                //todo ver si hay que acumular algo mas que no sea iva1 e importe sin iva.
                acumulador.get().setImporteSinIva(
                        losPesables.stream().reduce(
                                acumulador.get().getImporteSinIva()*acumulador.get().getCantidad(),
                                (prev, next) -> prev + next.getImporteSinIva()*next.getCantidad(), Double::sum));
                acumulador.get().setIVA1(
                        losPesables.stream().reduce(
                                acumulador.get().getIVA1()*acumulador.get().getCantidad(),
                                (prev, next) -> prev + next.getIVA1()*next.getCantidad(), Double::sum));
                acumulador.get().setImpInt(
                        losPesables.stream().reduce(
                                acumulador.get().getImpInt()*acumulador.get().getCantidad(),
                                (prev, next) -> prev + next.getImpInt()*next.getCantidad(), Double::sum));
                acumulador.get().setTotal(
                        losPesables.stream().reduce(
                                acumulador.get().getTotal()*acumulador.get().getCantidad(),
                                (prev, next) -> prev + next.getTotal()*next.getCantidad(), Double::sum));
                acumulador.get().setArticuloName(acumulador.get().getNroVendedors());
                acumulador.get().setCodArticulo(-1);
                acumulador.get().setCantidad(1d);
                result.add(acumulador.get());
                result.addAll(losNoDeberiaAgrupar);
            } else {
                result.addAll(evconts);
            }
        });
        return result;
    }

    /**
     * Toma la lista original de la base y netea los anulados y suma los mismos articulos para que esten en un mismo
     * regitro con mas de 1 en cantidad. Este ultimo agrupado no aplica para los pesables tipo 32 y 64.
     * @param source
     * @return
     */
    protected List<EvCont> agruparMismosArticulosNeteadoDeAnulados(List<EvCont> source){
        List <EvCont> result = new ArrayList<>();
        Map<String, List<EvCont>> grouped = source.stream().collect(Collectors.groupingBy(EvCont::getCodArticuloConcatOrigen));
        grouped.forEach((codigoArt, evconts) -> {
                    List<EvCont> acumulados = new ArrayList<>();
                    for (EvCont evCont : evconts) {
                        Optional<EvCont> prev = acumulados.stream().max((o1, o2) -> (int)(o1.getCantidad()-o2.getCantidad()));
                        if (evCont.getCantidad()<0
                                || (!evCont.isAgrupable() && !evCont.isPromocion())
                                || (prev.isPresent() && prev.get().getCantidad()<0)) {

                            if(prev.isPresent()){
                                prev.get().setCantidad(prev.get().getCantidad()+evCont.getCantidad());
                            } else{
                                acumulados.add(evCont);
                            }
                        } else {
                            acumulados.add(evCont);
                        }
                    }
                    result.addAll(acumulados.stream().filter(ev-> ev.getCantidad()>0).collect(Collectors.toList()));
                });
        return result;
    }
}
