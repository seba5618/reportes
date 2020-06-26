package ar.com.bambu.entities;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EvContTest {

    @Test
    public void montoExentoEsCeroSiArticuloNoEsExento() {
        EvCont toTest = this.build();
        toTest.getArticuloIVA().setCodIva(0);

        Double result = toTest.getMontoExento();

        Assert.assertEquals("Monto Exento deberia ser cero en articulo no exento", 0, result, 0.01d);

    }

    @Test
    public void montoExentoNoEsCeroSiArticuloEsExento() {
        EvCont toTest = this.build();
        toTest.getArticuloIVA().setCodIva(2);
        Double result = toTest.getMontoExento();
        Assert.assertEquals("Monto Exento deberia ser 10 en articulo exento", 10, result, 0.01d);
    }

    private EvCont build() {
        EvCont evCont = new EvCont();
        evCont.setTotal(10);
        Articulo articulo = new Articulo();
        ArticuloIva articuloIva = new ArticuloIva();
        return new EvCont(evCont, articulo, articuloIva);
    }
}