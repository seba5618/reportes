package ar.com.bambu.controlers;

import ar.com.bambu.entities.*;
import ar.com.bambu.models.Cotizacion;
import ar.com.bambu.repos.EvContRepository;
import ar.com.bambu.repos.EvMedioRepository;
import ar.com.bambu.repos.EventosRepository;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import java.util.List;


@RestController
@RequestMapping
public class ReporteCotizacion {


    String fileName="Wood.jasper";


    @Autowired
    EventosRepository repo;
    @Autowired
    EvContRepository repoCont;
    @Autowired
    EvMedioRepository medioRepository;

    @RequestMapping(path="/cotizacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource>  getCotizacion(@RequestBody Eventos ev) throws Exception{
        List<Eventos> all = repo.findAll();
        Eventos evento = repo.findById(new EventosId(ev.getIdEvento(), ev.getCajaZ())).get();
        List<EvCont> detalle = repoCont.findByIdEvento(ev.getIdEvento());
        List<EvCont> byIdEventoArtiName = repoCont.findByIdEventoArtiName(ev.getIdEvento());
        EvMedios pie = medioRepository.findByIdEventoWithMedioName(ev.getIdEvento());

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setCabecera(evento);
        cotizacion.setDetalle(byIdEventoArtiName);
        cotizacion.setPie(pie);
        byte[] pdf = createPDF(cotizacion);
        System.out.println(evento);
        detalle.forEach(c-> System.out.println(c.toString()));

        byIdEventoArtiName.forEach(c-> System.out.println("con nombre"+c.toString()));

        System.out.println("PIE"+pie);

        return ResponseEntity.ok().body(new ByteArrayResource(pdf));
    }


    private byte[] createPDF(Cotizacion cotizacion) throws  Exception{



        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(fileName);

        HashMap hm= new HashMap();
        hm.put("model",cotizacion);
        System.out.println(inputStream);
        JasperPrint print=JasperFillManager.fillReport(inputStream,hm,new JRBeanCollectionDataSource(cotizacion.getDetalle()));

        byte[] bytes = JasperExportManager.exportReportToPdf(print);


        return bytes;

    }
}
