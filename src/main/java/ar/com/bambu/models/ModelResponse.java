package ar.com.bambu.models;

import java.util.List;

public interface ModelResponse<Cabecera, Detalle, Pie> {
    Cabecera getCabecera();
    List<Detalle> getDetalle();
    Pie getPie();
}
