package ar.com.bambu.models;

import java.util.List;

public interface Model<Cabecera, Detalle, Pie> {
    Cabecera getCabecera();
    List<Detalle> getDetalle();
    Pie getPie();
}
