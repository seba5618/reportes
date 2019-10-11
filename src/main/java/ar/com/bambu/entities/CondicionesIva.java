package ar.com.bambu.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CondicionesIva {
    private Short status;
    @Id
    private short codCondIva;

    private String descCondIva;

}
