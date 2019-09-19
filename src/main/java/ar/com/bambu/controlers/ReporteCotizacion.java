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
    String outFileName="Wood.pdf";


    @Autowired
    EventosRepository repo;
    @Autowired
    EvContRepository repoCont;
    @Autowired
    EvMedioRepository medioRepository;

    @RequestMapping(path="/cotizacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource>  sendEmail(@RequestBody Eventos ev) throws Exception{
        List<Eventos> all = repo.findAll();
        Eventos evento = repo.findById(new EventosId(ev.getIdEvento(), ev.getCajaZ())).get();
        List<EvCont> detalle = repoCont.findByIdEvento(ev.getIdEvento());
        EvMedios pie = medioRepository.findByIdEvento(ev.getIdEvento());
        all.forEach(c-> System.out.println(c.getIdEvento()));
        byte[] pdf = createPDF(detalle);

        return ResponseEntity.ok().body(new ByteArrayResource(pdf));
    }

    private byte[] createPDF(List<EvCont> detalle) throws  Exception{



        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(fileName);

        HashMap hm= new HashMap();
        JasperPrint print=JasperFillManager.fillReport(inputStream,hm,new JRBeanCollectionDataSource(detalle));

        byte[] bytes = JasperExportManager.exportReportToPdf(print);


        return bytes;

    }
}
