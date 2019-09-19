package ar.com.bambu.repos;

import ar.com.bambu.entities.EvCont;
import ar.com.bambu.entities.EventosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvContRepository extends JpaRepository<EvCont, EventosId> {
    public List<EvCont> findByIdEvento(Long idEvento);
}
