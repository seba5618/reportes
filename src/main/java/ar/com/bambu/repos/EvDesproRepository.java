package ar.com.bambu.repos;

import ar.com.bambu.entities.EvDespro;
import ar.com.bambu.entities.EvDesproId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvDesproRepository extends JpaRepository<EvDespro, EvDesproId> {

        @Query(value = "select new ar.com.bambu.entities.EvDespro (ev) from EvDespro   where ev.idEvento=:idEvento and ev.cajaZ=:cajaZ")
        public List<EvDespro> findByIdEventoCajaZ(@Param("idEvento") Long idEvento, @Param("cajaZ") Long cajaZ);

}
