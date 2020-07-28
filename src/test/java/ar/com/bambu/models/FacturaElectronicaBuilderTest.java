package ar.com.bambu.models;

import ar.com.bambu.entities.EvCont;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FacturaElectronicaBuilderTest {


    private FacturaElectronicaBuilder toTest = new FacturaElectronicaBuilder();

    @Test
    void agruparPorNroVendedorS() {
        List<EvCont> result = toTest.agruparPorNroVendedorS(this.listForTest());
        Assert.assertTrue("El tamaño de la lista deberia ser de 7, es: "+ result.size(), result.size() == 7);
        EvCont agrupar = result.stream().filter(evCont -> evCont.getNroVendedors().equals("agrupar")).findFirst().get();
        Assert.assertTrue("La suma de monto sin iva de grupo agrupar deberia ser 5.5 y es" + agrupar.getImporteSinIva(), Math.abs(agrupar.getImporteSinIva() - 5.5d) < 0.01);
        agrupar = result.stream().filter(evCont -> evCont.getNroVendedors().equals("otro")).findFirst().get();
        Assert.assertTrue("La suma de monto sin iva de grupo otro deberia ser 3 y es" + agrupar.getImporteSinIva(), Math.abs(agrupar.getImporteSinIva() -  3) < 0.01);
    }

    @Test
    void agruparPorNroVendedorS2() {
        List<EvCont> result = toTest.agruparPorNroVendedorS(this.listForTest());
        Assert.assertTrue("El tamaño de la lista deberia ser de 7, es: "+ result.size(), result.size() == 7);
        EvCont agrupar = result.stream().filter(evCont -> evCont.getNroVendedors().equals("agrupar")).findFirst().get();
        Assert.assertTrue("La suma de monto sin iva de grupo agrupar deberia ser 5.5 y es" + agrupar.getImporteSinIva(), Math.abs(agrupar.getImporteSinIva() - 5.5d) < 0.01);
        agrupar = result.stream().filter(evCont -> evCont.getNroVendedors().equals("otro")).findFirst().get();
        Assert.assertTrue("La suma de monto sin iva de grupo otro deberia ser 3 y es" + agrupar.getImporteSinIva(), Math.abs(agrupar.getImporteSinIva() -  3) < 0.01);
    }

    @Test
    void agruparPorAnuladosYRepetidos() {
        List<EvCont> result = toTest.agruparMismosArticulosNeteadoDeAnulados(this.listForTest());
        Assert.assertTrue("El tamaño de la lista deberia ser de 6, es: "+ result.size(), result.size() == 6);
    }

    @Test
    void agruparPorAnuladosYRepetidos2() {
        List<EvCont> result = toTest.agruparMismosArticulosNeteadoDeAnulados(this.listForTestAnulados());
        Assert.assertTrue("El tamaño de la lista deberia ser de 5, es: "+ result.size(), result.size() == 5);
        EvCont dos = result.stream().filter(evCont -> evCont.getCodArticulo() == 2 && evCont.getOrigen() == 1).findFirst().get();
        assertEquals(4.0, dos.getCantidad().doubleValue(), "cantidad del articulo 2 deberia ser 4");
    }

    @Test
    void agruparPorAnuladosYRepetidosListaVacia() {
        List<EvCont> result = toTest.agruparMismosArticulosNeteadoDeAnulados(Collections.EMPTY_LIST);
        Assert.assertTrue("El tamaño de la lista deberia ser de 0, es: "+ result.size(), result.size() == 0);
    }

    @Test
    void agruparPorVendedorSListaVacia() {
        List<EvCont> result = toTest.agruparPorNroVendedorS(Collections.EMPTY_LIST);
        Assert.assertTrue("El tamaño de la lista deberia ser de 0, es: "+ result.size(), result.size() == 0);
    }



    @Test
    public void withDetalleEvento110() throws Exception {
        //when
        this.toTest.withDetalle(this.fromJsonFile(110));
        //then
        assertEquals(4,toTest.detalle.size(), "Evento 110 deberian ser 4 articulos finales.");
        List<EvCont> the394 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 394).collect(Collectors.toList());
        assertEquals(2,the394.size(), "Evento 110 deberian ser 2 articulos 394.");
        Optional<EvCont> any = the394.stream().filter(ev -> ev.getImporteSinIva() - 20661.086d < 0.01).findAny();
        assertTrue(any.isPresent(), "Tiene que haber un articulo 394 cuya suma de importe sin ivas sea 20661.086");
        any = the394.stream().filter(ev -> ev.getImporteSinIva() - 15163.348d < 0.01).findAny();
        assertTrue(any.isPresent(), "Tiene que haber un articulo 394 cuya suma de importe sin ivas sea 15163.348d");
        List<EvCont> the512 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 512).collect(Collectors.toList());
        assertEquals(1,the512.size(), "Evento 110 deberian ser 1 articulos 512.");
        List<EvCont> the999990 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 999990).collect(Collectors.toList());
        assertEquals(1,the999990.size(), "Evento 110 deberian ser 1 articulos 999990.");
    }

    @Test
    public void withDetalleEvento40() throws Exception {
        //when
        this.toTest.withDetalle(this.fromJsonFile(40));
        //then
        assertEquals(3,toTest.detalle.size(), "Evento 40 deberian ser 3 articulos finales.");
//        List<EvCont> the394 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 394).collect(Collectors.toList());
//        assertEquals(2,the394.size(), "Evento 110 deberian ser 2 articulos 394.");
//        Optional<EvCont> any = the394.stream().filter(ev -> ev.getImporteSinIva() - 20661.086d < 0.01).findAny();
//        assertTrue(any.isPresent(), "Tiene que haber un articulo 394 cuya suma de importe sin ivas sea 20661.086");
//        any = the394.stream().filter(ev -> ev.getImporteSinIva() - 15163.348d < 0.01).findAny();
//        assertTrue(any.isPresent(), "Tiene que haber un articulo 394 cuya suma de importe sin ivas sea 15163.348d");
//        List<EvCont> the512 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 512).collect(Collectors.toList());
//        assertEquals(1,the512.size(), "Evento 110 deberian ser 1 articulos 512.");
//        List<EvCont> the999990 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 999990).collect(Collectors.toList());
//        assertEquals(1,the999990.size(), "Evento 110 deberian ser 1 articulos 999990.");
    }


    /**
     *  Lee el Json de evcont en resources y devuelve una lista filtrada por evento
     * @param evento
     * @return
     * @throws Exception
     */
    private List<EvCont> fromJsonFile(int evento) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        ClassPathResource a = new ClassPathResource("json.json");
        EvCont[] evConts = mapper.readValue(a.getFile(), EvCont[].class);
        return Arrays.stream(evConts).filter(ev -> ev.getIdEvento() == evento).collect(Collectors.toList());
    }

    private List<EvCont> listForTest(){
        List<EvCont> result = new ArrayList<>();

        EvCont evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setTipo3(33);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("agrupar");
        evCont.setTipo3(65);
        evCont.setImporteSinIva(1d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("agrupar");
        evCont.setTipo3(65);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("otro");
        evCont.setTipo3(33);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("otro");
        evCont.setTipo3(65);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("otro");
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(-1.0);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setImporteSinIva(1.5d);
        evCont.setCodArticulo(1);
        evCont.setCantidad(-1.0);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(5);
        evCont.setCantidad(1.0);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        result.add(evCont);

        return result;
    }

    private List<EvCont> listForTestAnulados() {
        List<EvCont> result = new ArrayList<>();
        EvCont evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setTipo3(33);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setTipo3(40);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(-1.0);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(-1.0);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(2.0);
        evCont.setOrigen(1);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(2.0);
        evCont.setOrigen(1);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(2.0);
        evCont.setOrigen(4);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(20);
        evCont.setCantidad(2.0);
        evCont.setOrigen(4);
        evCont.setTipo3(11);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        return result;
    }


}