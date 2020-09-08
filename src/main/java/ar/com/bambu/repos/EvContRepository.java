package ar.com.bambu.repos;

import ar.com.bambu.entities.EvCont;
import ar.com.bambu.entities.EvContId;
import ar.com.bambu.entities.EventosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvContRepository extends JpaRepository<EvCont, EvContId> {
    public List<EvCont> findByIdEvento(Long idEvento);

    @Query(value = "select new ar.com.bambu.entities.EvCont (ev, art, aIVA) " +
            "from EvCont ev join Articulo art on ev.codArticulo=art.codInterno " +
            " join ArticuloIva aIVA on art.codIva = aIVA.codIva" +
            " where ev.idEvento=:idEvento and ev.cajaZ=:cajaZ")
    public List<EvCont> findByIdEventoArtiName(@Param("idEvento") Long idEvento,@Param("cajaZ") Long cajaZ);

}
