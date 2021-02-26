package ar.com.bambu.models;

import ar.com.bambu.entities.EvCont;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
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
    void agruparAlgunosYOtrosNo() {
        List<EvCont> result = toTest.agruparMismosArticulosNeteadoDeAnulados(this.agruparCompuestosyNoOtros());
        Map<String, List<EvCont>> grouped = result.stream().collect(Collectors.groupingBy(EvCont::getCodArticuloConcatOrigenTipo3));
        Assert.assertTrue("Agrupar unos y otros no El tamaño de la lista deberia ser de 4, es: "+ grouped.size(), grouped.size() == 4);
    }

    @Test
    void agruparPorVendedorSListaVacia() {
        List<EvCont> result = toTest.agruparPorNroVendedorS(Collections.EMPTY_LIST);
        Assert.assertTrue("El tamaño de la lista deberia ser de 0, es: "+ result.size(), result.size() == 0);
    }

    @Test
    void agruparPromocionesAnuladas() {
        List<EvCont> result = new ArrayList<>();
        EvCont evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setTipo3(33);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(4);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(-1.0);
        evCont.setTipo3(33);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        evCont.setOrigen(4);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(-1.0);
        evCont.setTipo3(33);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(4);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setTipo3(33);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        evCont.setOrigen(4);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(-1.0);
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        toTest.withDetalle(result);

        Assert.assertTrue("El tamaño de la lista deberia ser de 0, es: "+ toTest.detalle.size(), toTest.detalle.size() == 0);
    }


    @Test
    List<EvCont> agruparCompuestosyNoOtros() {
        List<EvCont> result = new ArrayList<>();
        EvCont evCont = new EvCont();
        evCont.setCodArticulo(4071);
        evCont.setCantidad(1.0);
        evCont.setTipo3(0);
        evCont.setNroVendedors("");
        evCont.setImporteSinIva(2424.268);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(2933.36);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1244);
        evCont.setCantidad(0.999994);
        evCont.setTipo3(33);
        evCont.setNroVendedors("primero");
        evCont.setImporteSinIva(644.8635);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(780.28);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1310);
        evCont.setCantidad(0.197999);
        evCont.setTipo3(65);
        evCont.setNroVendedors("primero");
        evCont.setImporteSinIva(6184.464);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(1481.67);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1244);
        evCont.setCantidad(0.999994);
        evCont.setTipo3(33);
        evCont.setNroVendedors("segundo");
        evCont.setImporteSinIva(644.8635);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(780.28);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1310);
        evCont.setCantidad(0.0319997);
        evCont.setTipo3(65);
        evCont.setNroVendedors("segundo");
        evCont.setImporteSinIva(6184.464);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(239.46);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1244);
        evCont.setCantidad(0.999994);
        evCont.setTipo3(0);
        evCont.setNroVendedors("");
        evCont.setImporteSinIva(644.8635);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(780.28);
        evCont.setOrigen(1);
        result.add(evCont);
        return result;
    }


    @Test
    public void withDetalleEvento110() throws Exception {
        //when
        this.toTest.withDetalle(this.fromJsonFile(110));
        //then
        assertEquals(4,toTest.detalle.size(), "Evento 110 deberian ser 4 evcont finales.");
        List<EvCont> theAcumuladores = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == -1).collect(Collectors.toList());
        assertEquals(2,theAcumuladores.size(), "Evento 110 deberian ser 2 evcont acumuladores.");
        Optional<EvCont> any = theAcumuladores.stream().filter(ev -> ev.getImporteSinIva() - 9356.583d < 0.01).findAny();
        assertTrue(any.isPresent(), "Tiene que haber un evcont 394 cuya suma de importe sin ivas sea 9356.583");
        any = theAcumuladores.stream().filter(ev -> ev.getImporteSinIva() - 9506.49 < 0.01).findAny();
        assertTrue(any.isPresent(), "Tiene que haber un evcont 394 cuya suma de importe sin ivas sea 9506.49");
        List<EvCont> the512 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 512).collect(Collectors.toList());
        assertEquals(1,the512.size(), "Evento 110 deberian ser 1 evcont 512.");
        List<EvCont> the999990 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 999990).collect(Collectors.toList());
        assertEquals(1,the999990.size(), "Evento 110 deberian ser 1 evcont 999990.");
    }

    @Test
    public void withDetalleEvento40() throws Exception {
        //when
        this.toTest.withDetalle(this.fromJsonFile(40));
        //then
        assertEquals(4,toTest.detalle.size(), "Evento 40 deberian ser 4 evcont finales.");
        List<EvCont> the2360 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 2360).collect(Collectors.toList());
        assertEquals(1,the2360.size(), "Evento 40 deberian ser 1 evcont 2360.");
        assertEquals(Double.valueOf(2d),the2360.get(0).getCantidad(), "Tiene que haber 2 de cantidad en el evcont 2360");

        List<EvCont> the1319 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 1319).collect(Collectors.toList());
        assertEquals(3,the1319.size(), "Evento 40 deberian ser 3 evcont 1319.");
        the1319.forEach(evCont -> assertTrue(evCont.getCantidad() == 1, "Evento 40 todo evecont 1319 con cantidad 1 deberia."));
    }

    @Test
    public void withDetalleEvento26() throws Exception {
        //when
        this.toTest.withDetalle(this.fromJsonFile(26));
        //then
        assertEquals(7,toTest.detalle.size(), "Evento 26 deberian ser 4 evcont finales.");
        List<EvCont> the629 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 629).collect(Collectors.toList());
        assertEquals(2,the629.size(), "Evento 26 deberian ser 2 evcont 629.");
        assertEquals(1,the629.stream().filter(evCont -> evCont.getOrigen()==4).collect(Collectors.toList()).size(), "Evento 26 deberia tener solo una promo evcont 629");

        List<EvCont> the1263 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 1263).collect(Collectors.toList());
        assertEquals(2,the1263.size(), "Evento 26 deberian ser 2 evcont 1263.");
        assertEquals(1,the1263.stream().filter(evCont -> evCont.isPromocion()).collect(Collectors.toList()).size(), "Evento 26 deberia tener solo una promo evcont 1263");
    }

    @Test
    public void withDetalleEvento30() throws Exception {
        //when
        this.toTest.withDetalle(this.fromJsonFile(30));
        //then
        assertEquals(5,toTest.detalle.size(), "Evento 30 deberian ser 5 evcont finales.");
     /*   List<EvCont> the629 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 629).collect(Collectors.toList());
        assertEquals(2,the629.size(), "Evento 26 deberian ser 2 evcont 629.");
        assertEquals(1,the629.stream().filter(evCont -> evCont.getOrigen()==4).collect(Collectors.toList()).size(), "Evento 26 deberia tener solo una promo evcont 629");

        List<EvCont> the1263 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 1263).collect(Collectors.toList());
        assertEquals(2,the1263.size(), "Evento 26 deberian ser 2 evcont 1263.");
        assertEquals(1,the1263.stream().filter(evCont -> evCont.isPromocion()).collect(Collectors.toList()).size(), "Evento 26 deberia tener solo una promo evcont 1263");*/
    }

    @Test
    public void withDetalleEvento29() throws Exception {
        //when
        this.toTest.withDetalle(this.fromJsonFile(29));
        //then
        assertEquals(13,toTest.detalle.size(), "Evento 29 deberian ser 13 evcont finales.");
     /*   List<EvCont> the629 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 629).collect(Collectors.toList());
        assertEquals(2,the629.size(), "Evento 26 deberian ser 2 evcont 629.");
        assertEquals(1,the629.stream().filter(evCont -> evCont.getOrigen()==4).collect(Collectors.toList()).size(), "Evento 26 deberia tener solo una promo evcont 629");

        List<EvCont> the1263 = toTest.detalle.stream().filter(ev -> ev.getCodArticulo() == 1263).collect(Collectors.toList());
        assertEquals(2,the1263.size(), "Evento 26 deberian ser 2 evcont 1263.");
        assertEquals(1,the1263.stream().filter(evCont -> evCont.isPromocion()).collect(Collectors.toList()).size(), "Evento 26 deberia tener solo una promo evcont 1263");*/
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
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(1.0);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setNroVendedors("agrupar");
        evCont.setTipo3(65);
        evCont.setImporteSinIva(1d);
        evCont.setIVA1(0d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("agrupar");
        evCont.setTipo3(65);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        evCont.setOrigen(1);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("otro");
        evCont.setTipo3(33);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("otro");
        evCont.setTipo3(65);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("otro");
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(-1.0);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setImporteSinIva(1.5d);
        evCont.setCodArticulo(1);
        evCont.setCantidad(-1.0);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(5);
        evCont.setCantidad(1.0);
        evCont.setImporteSinIva(1.5d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
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
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setTipo3(40);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setIVA1(0d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(-1.0);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        evCont.setOrigen(1);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(1.0);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setIVA1(0d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(1);
        evCont.setCantidad(-1.0);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(2.0);
        evCont.setOrigen(1);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(2.0);
        evCont.setOrigen(1);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setIVA1(0d);
        evCont.setOrigen(1);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(2);
        evCont.setCantidad(2.0);
        evCont.setOrigen(4);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        evCont = new EvCont();
        evCont.setCodArticulo(20);
        evCont.setCantidad(2.0);
        evCont.setOrigen(4);
        evCont.setImpInt(0d);
        evCont.setTotal(3d);
        evCont.setTipo3(11);
        evCont.setNroVendedors("agrupar");
        evCont.setImporteSinIva(3d);
        evCont.setIVA1(0d);
        result.add(evCont);

        return result;
    }


}