package ar.com.bambu.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tpvconfig")
public class TpvConfig {
    private Short status;
    @Id
    private Integer sucursal;

    public TpvConfig(Integer sucursal) {
        this.sucursal = sucursal;
    }

    public Short getStatus() {
        return status;
    }
    public int getSucursal() {
        return sucursal;
    }
    public void setSucursal(int suc) {
        this.sucursal = suc;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
