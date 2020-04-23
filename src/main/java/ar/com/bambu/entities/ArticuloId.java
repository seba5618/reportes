package ar.com.bambu.entities;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticuloId)) return false;
        ArticuloId that = (ArticuloId) o;
        return codInterno == that.codInterno &&
                Objects.equals(codigoBarra, that.codigoBarra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codInterno, codigoBarra);
    }
}
