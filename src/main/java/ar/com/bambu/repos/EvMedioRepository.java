package ar.com.bambu.repos;

import ar.com.bambu.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvMedioRepository extends JpaRepository<EvMedios, EvMediosId> {

    EvMedios findByIdEvento(Long idEvento);

    @Query(value = "select new ar.com.bambu.entities.EvMedios (ev, medio.nombre as medioNombre) from EvMedios ev join Medios medio on ev.modoPago=medio.codMedio where ev.idEvento=:idEvento")
    EvMedios findByIdEventoWithMedioName(@Param("idEvento")Long idEvento);

}
