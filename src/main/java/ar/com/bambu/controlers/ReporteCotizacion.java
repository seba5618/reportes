package ar.com.bambu.controlers;

import ar.com.bambu.entities.EvCont;
import ar.com.bambu.entities.Eventos;
import ar.com.bambu.models.Cotizacion;
import ar.com.bambu.repos.EvContRepository;
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
    HashMap hm= new HashMap();

    @Autowired
    EventosRepository repo;
    @Autowired
    EvContRepository repoCont;
    @RequestMapping(path="/cotizacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource>  sendEmail(@RequestBody Eventos evento) throws Exception{
        List<Eventos> all = repo.findAll();
        List<EvCont> detalle = repoCont.findByIdEvento(evento.getIdEvento());
        all.forEach(c-> System.out.println(c.getIdEvento()));
        byte[] pdf = createPDF(detalle);

        return ResponseEntity.ok().body(new ByteArrayResource(pdf));
    }

    private byte[] createPDF(List<EvCont> detalle) throws  Exception{



        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(fileName);


        JasperPrint print=JasperFillManager.fillReport(inputStream,hm,new JRBeanCollectionDataSource(detalle));

        byte[] bytes = JasperExportManager.exportReportToPdf(print);

        JRExporter exporter=new net.sf.jasperreports.engine.export.JRPdfExporter();
        JRPdfExporter jrPdfExporter = new JRPdfExporter();

        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,outFileName);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT,print);
        exporter.exportReport();
        System.out.println("Createdfile:"+outFileName);
        return bytes;

    }
}
