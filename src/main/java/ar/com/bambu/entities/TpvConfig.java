package ar.com.bambu.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tpvconfig")
public class TpvConfig {


    @Id
    private Integer sucursal;

    private String cuit;


    public int getSucursal() {
        return sucursal;
    }
    public void setSucursal(int suc) {
        this.sucursal = suc;
    }

    public String getCuit() {
        return cuit;
    }
    public void setCuit(String cuit ) {
        this.cuit = cuit;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
