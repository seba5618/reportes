package ar.com.bambu.repos;

import ar.com.bambu.entities.Eventos;
import ar.com.bambu.entities.EventosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<Eventos, EventosId> {


}
