package ar.com.bambu.controlers;

import ar.com.bambu.ReporterApplication;
import ar.com.bambu.entities.*;
import ar.com.bambu.models.FacturaElectronicaBuilder;
import ar.com.bambu.models.impl.Cotizacion;
import ar.com.bambu.models.impl.FacturaElectronica;
import ar.com.bambu.repos.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(path = "/reporte")
public class Reportes {


    @Value("${jasper.file.cotizacion}")
    String fileNameCotizacion;
    @Value("${jasper.file.factura}")
    String fileNameFactura;
    @Value("${jasper.file.remito}")
    String fileNameRemito;
    @Autowired
    EventosRepository repo;
    @Autowired
    EvContRepository repoCont;
    @Autowired
    EvMedioRepository medioRepository;
    @Autowired
    ClientesRepository clientesRepository;
    @Autowired
    TpvConfigRepository tpvConfigRepository;
    @Autowired
    CajerosRepository cajerosRepository;
    @Autowired
    FactuMemRepository factuMemRepository;

    private final static Logger LOGGER = LoggerFactory.getLogger(ReporterApplication.class);

    @RequestMapping(path = "/destroy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public void getDestroy(@RequestBody Eventos ev) throws Exception {
        System.exit(0);
    }
    

    @RequestMapping(path = "/cotizacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> getCotizacion(@RequestBody Eventos ev) throws Exception {
        Eventos evento;
        try {
            evento = repo.findById(new EventosId(ev.getIdEvento(), ev.getCajaZ())).get();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().eTag("Combinación evento: " + ev.getIdEvento() + " y cajaZ: " + ev.getCajaZ() + " no válida.").build();
        }

        Clientes clientes = clientesRepository.findByCodClienteConCondicionIva(evento.getCodCliente());
        TpvConfig sucursal = tpvConfigRepository.findByCodSucusal();
        Cajeros cajeros = cajerosRepository. findByCodCajero(evento.getNroVendedor1());
        //id =18 es el mail id=20 el telefono
        FactuMem factuMem = factuMemRepository.findById(18);

        if(cajeros == null ) {
            System.out.println("***** SONAMOS NO HAY CAJERO EN ESE VENDEDOR" + evento.getNroVendedor1() );
        }

        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(fileNameCotizacion);


        FacturaElectronicaBuilder facturaElectronicaBuilder = new FacturaElectronicaBuilder();
        List<EvCont> byIdEventoArtiName = repoCont.findByIdEventoArtiName(ev.getIdEvento());

        //EvMedios pie = medioRepository.findByIdEventoWithMedioName(ev.getIdEvento()).get(0);
        facturaElectronicaBuilder.withEvento(evento).withDetalle(byIdEventoArtiName).withCliente(clientes).withTpvconfig(sucursal).withCajeros(cajeros).withFactuMem(factuMem);


        FacturaElectronica facturaElectronica = facturaElectronicaBuilder.build();


        JasperPrint print = JasperFillManager.fillReport(inputStream, new HashMap(), new JRBeanCollectionDataSource(facturaElectronica.getDetalle()));
        byte[] bytes = JasperExportManager.exportReportToPdf(print);
        ResponseEntity<ByteArrayResource> response = ResponseEntity.ok().header("Content-Disposition", "attachment; filename=factura.pdf").body(new ByteArrayResource(bytes));
        return response;

      /*  desde aca es cotizacion vieja
        List<EvCont> byIdEventoArtiName = repoCont.findByIdEventoArtiName(ev.getIdEvento());
        //EvMedios pie = medioRepository.findByIdEventoWithMedioName(ev.getIdEvento()).get(0);

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setCabecera(evento);
        cotizacion.setDetalle(byIdEventoArtiName);
        //cotizacion.setPie(pie);

        byte[] pdf = createPDF(cotizacion);
        ResponseEntity<ByteArrayResource> response = ResponseEntity.ok().header("Content-Disposition", "attachment; filename=taa.pdf").body(new ByteArrayResource(pdf));

        return response;

       */
    }

    @RequestMapping(path = "/factura", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> getFactura(@RequestBody Eventos ev) throws Exception {
        Eventos evento;

        try {
            evento = repo.findById(new EventosId(ev.getIdEvento(), ev.getCajaZ())).get();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().eTag("Combinación evento: " + ev.getIdEvento() + " y cajaZ: " + ev.getCajaZ() + " no válida.").build();
        }


        Clientes clientes = clientesRepository.findByCodClienteConCondicionIva(evento.getCodCliente());
        TpvConfig sucursal = tpvConfigRepository.findByCodSucusal();

        //traemos el nro de punto de venta electronica (14)
        FactuMem factuMem = factuMemRepository.findById(14);
        LOGGER.info("Nro punto venta electronica " +factuMem.getValor() );
        System.out.println("***** PUNTO DE VENTA ****");
        System.out.println(factuMem.getValor());

        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(fileNameFactura);


        FacturaElectronicaBuilder facturaElectronicaBuilder = new FacturaElectronicaBuilder();
        List<EvCont> byIdEventoArtiName = repoCont.findByIdEventoArtiName(ev.getIdEvento());
        EvMedios pie = medioRepository.findByIdEventoWithMedioName(ev.getIdEvento()).get(0);
        facturaElectronicaBuilder.withEvento(evento).withDetalle(byIdEventoArtiName).withPie(pie).withCliente(clientes).withTpvconfig(sucursal).withFactuMem(factuMem);


        FacturaElectronica facturaElectronica = facturaElectronicaBuilder.build();


        JasperPrint print = JasperFillManager.fillReport(inputStream, new HashMap(), new JRBeanCollectionDataSource(facturaElectronica.getDetalle()));
        byte[] bytes = JasperExportManager.exportReportToPdf(print);
        ResponseEntity<ByteArrayResource> response = ResponseEntity.ok().header("Content-Disposition", "attachment; filename=factura.pdf").body(new ByteArrayResource(bytes));
        return response;
    }

    @RequestMapping(path = "/remito", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> getRemito(@RequestBody Eventos ev) throws Exception {
        Eventos evento;

        try {
            evento = repo.findById(new EventosId(ev.getIdEvento(), ev.getCajaZ())).get();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().eTag("Combinación evento: " + ev.getIdEvento() + " y cajaZ: " + ev.getCajaZ() + " no válida.").build();
        }


        Clientes clientes = clientesRepository.findByCodClienteConCondicionIva(evento.getCodCliente());
        Cajeros cajeros = cajerosRepository. findByCodCajero(evento.getNroVendedor1());
        if(cajeros == null ) {
            System.out.println("***** SONAMOS NO HAY CAJERO EN ESE VENDEDOR****");
        }
        TpvConfig sucursal = tpvConfigRepository.findByCodSucusal();
        FactuMem factuMem = factuMemRepository.findById(20);

        System.out.println("***** VIENDO EL CODIGO DE SUCURSAL****");
        System.out.println(Integer.toString(sucursal.getSucursal()));

        System.out.println("***** VIENDO TELEFONO ****");
        System.out.println(factuMem.getValor());

        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(fileNameRemito);
        InputStream inputStream2 = getClass()
                .getClassLoader().getResourceAsStream(fileNameRemito);

        FacturaElectronicaBuilder facturaElectronicaBuilder = new FacturaElectronicaBuilder();
        List<EvCont> byIdEventoArtiName = repoCont.findByIdEventoArtiName(ev.getIdEvento());
        //EvMedios pie = medioRepository.findByIdEventoWithMedioName(ev.getIdEvento()).get(0);
        facturaElectronicaBuilder.withEvento(evento).withDetalle(byIdEventoArtiName).withCliente(clientes).withTpvconfig(sucursal).withCajeros(cajeros).withFactuMem(factuMem);
        //facturaElectronicaBuilder.withEvento(evento).withDetalle(byIdEventoArtiName).withPie(pie).withCliente(clientes);


        FacturaElectronica facturaElectronica = facturaElectronicaBuilder.build();

        //codigo mancuso para multiples copia
        ArrayList<JasperPrint> jasperPrints = new ArrayList<>();
        //List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
        //

        JasperPrint print = JasperFillManager.fillReport(inputStream, new HashMap(), new JRBeanCollectionDataSource(facturaElectronica.getDetalle()));
        JasperPrint print2 = JasperFillManager.fillReport(inputStream2, new HashMap(), new JRBeanCollectionDataSource(facturaElectronica.getDetalle()));

        //codigo mancuso duplicamos el reporte
        jasperPrints.add(print);
        jasperPrints.add(print2);
        JRPdfExporter exporter = new JRPdfExporter();
//Create new FileOutputStream or you can use Http Servlet Response.getOutputStream() to get Servlet output stream
// Or if you want bytes create ByteArrayOutputStream
       //
         ByteArrayOutputStream out = new ByteArrayOutputStream();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);

       // exporter.exportReport();
        //byte[] bytes = out.toByteArray();

        byte[] bytes = this.exportToPdf(jasperPrints);
        // hasta aca codigo mancuso
        //codigo seba
        //byte[] bytes = JasperExportManager.exportReportToPdf(print);
        //
        ResponseEntity<ByteArrayResource> response = ResponseEntity.ok().header("Content-Disposition", "attachment; filename=factura.pdf").body(new ByteArrayResource(bytes));
        return response;
    }

    private byte[] exportToPdf(List<JasperPrint> jasperPrint) throws JRException, JRException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
        exporter.exportReport();
        return baos.toByteArray();
    }

    private byte[] createPDF(Cotizacion cotizacion) throws Exception {
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(fileNameCotizacion);
        HashMap hm = new HashMap();
        hm.put("model", cotizacion);
        System.out.println("***** VIENDO EL INPUTSTRING");
        System.out.println(inputStream);

        JasperPrint print = JasperFillManager.fillReport(inputStream, hm, new JRBeanCollectionDataSource(cotizacion.getDetalle()));

        return JasperExportManager.exportReportToPdf(print);
    }
}
