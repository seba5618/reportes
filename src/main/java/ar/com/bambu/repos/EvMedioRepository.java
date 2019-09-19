package ar.com.bambu.repos;

import ar.com.bambu.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvMedioRepository extends JpaRepository<EvMedios, EvMediosId> {

    EvMedios findByIdEvento(Long idEvento);

}
