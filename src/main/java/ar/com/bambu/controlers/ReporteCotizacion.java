package ar.com.bambu.controlers;

import ar.com.bambu.entities.EvCont;
import ar.com.bambu.entities.EvMedios;
import ar.com.bambu.entities.Eventos;
import ar.com.bambu.entities.EventosId;
import ar.com.bambu.models.Cotizacion;
import ar.com.bambu.repos.EvContRepository;
import ar.com.bambu.repos.EvMedioRepository;
import ar.com.bambu.repos.EventosRepository;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping
public class ReporteCotizacion {


    @Value("${jasper.file}")
    String fileName;

    @Autowired
    EventosRepository repo;
    @Autowired
    EvContRepository repoCont;
    @Autowired
    EvMedioRepository medioRepository;


    @RequestMapping(path = "/cotizacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> getCotizacion(@RequestBody Eventos ev) throws Exception {
        Eventos evento;
        try {
            evento = repo.findById(new EventosId(ev.getIdEvento(), ev.getCajaZ())).get();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().eTag("Combinación evento: " + ev.getIdEvento() + " y cajaZ: " + ev.getCajaZ() + " no válida.").build();
        }

        List<EvCont> byIdEventoArtiName = repoCont.findByIdEventoArtiName(ev.getIdEvento());
        EvMedios pie = medioRepository.findByIdEventoWithMedioName(ev.getIdEvento());

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setCabecera(evento);
        cotizacion.setDetalle(byIdEventoArtiName);
        cotizacion.setPie(pie);

        byte[] pdf = createPDF(cotizacion);
        ResponseEntity<ByteArrayResource> response = ResponseEntity.ok().header("Content-Disposition", "attachment; filename=taa.pdf").body(new ByteArrayResource(pdf));

        return response;
    }

    private byte[] createPDF(Cotizacion cotizacion) throws Exception {
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(fileName);
        HashMap hm = new HashMap();
        hm.put("model", cotizacion);
        System.out.println("***** VIENDO EL INPUTSTRING");
        System.out.println(inputStream);
        JasperPrint print = JasperFillManager.fillReport(inputStream, hm, new JRBeanCollectionDataSource(cotizacion.getDetalle()));

        return JasperExportManager.exportReportToPdf(print);
    }
}
