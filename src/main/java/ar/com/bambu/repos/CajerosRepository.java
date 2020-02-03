package ar.com.bambu.repos;

import ar.com.bambu.entities.Cajeros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CajerosRepository extends JpaRepository<Cajeros, Integer> {

    @Query(value = "select new ar.com.bambu.entities.Cajeros (ca.nombreCajero) from Cajeros ca  where ca.codCajero=:codCajero")
    Cajeros findByCodCajero(@Param("codCajero") int codCajero);
}

