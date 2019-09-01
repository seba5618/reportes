package ar.com.bambu.controlers;

import ar.com.bambu.entities.Eventos;
import ar.com.bambu.models.Cotizacion;
import ar.com.bambu.repos.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping
public class ReporteCotizacion {

    @Autowired
    EventosRepository repo;
    @RequestMapping(path="/cotizacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Cotizacion sendEmail(@RequestBody Eventos evento) throws Exception{
        List<Eventos> all = repo.findAll();
        all.forEach(c-> System.out.println(c.getIdEvento()));

        return new Cotizacion();
    }
}
