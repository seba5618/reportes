package ar.com.bambu.models;

import ar.com.bambu.entities.Clientes;
import ar.com.bambu.entities.EvCont;
import ar.com.bambu.entities.EvMedios;
import ar.com.bambu.entities.Eventos;
import ar.com.bambu.models.impl.FacturaDetalle;
import ar.com.bambu.models.impl.FacturaElectronica;

import java.util.List;

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



    public FacturaElectronicaBuilder withEvento(Eventos ev){
        this.cabecera=ev;
        return this;
    }

    public FacturaElectronicaBuilder withDetalle(List<EvCont> detalle){
        this.detalle=detalle;
        return this;
    }

    public FacturaElectronicaBuilder withPie(EvMedios ev){
        this.pie=ev;
        return this;
    }

    public FacturaElectronicaBuilder withCliente(Clientes cl){
        this.clientes =cl;
        return this;
    }

    public FacturaElectronica build(){
        FacturaElectronica result = new FacturaElectronica();

        detalle.forEach(c->{
           FacturaDetalle detalle = new FacturaDetalle();
           detalle.setTipoComprobante(this.cabecera.getTipoEvento());
           detalle.setNumeroYTipoComprobante(this.cabecera.getCaja(), this.cabecera.getNroTicket());
           detalle.setDetalleFactura(c);
           detalle.setDataCliente(this.clientes);
           result.addDetalle(detalle);
        });

        return result;
    }
}
