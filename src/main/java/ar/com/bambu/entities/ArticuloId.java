package ar.com.bambu.entities;

import java.io.Serializable;

public class ArticuloId implements Serializable {

    private int codInterno;
    private String codigoBarra;

    public int getCodInterno() {
        return codInterno;
    }

    public void setCodInterno(int codInterno) {
        this.codInterno = codInterno;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }
}
