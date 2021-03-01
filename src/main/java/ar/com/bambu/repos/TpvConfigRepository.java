package ar.com.bambu.repos;

import ar.com.bambu.entities.TpvConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TpvConfigRepository extends JpaRepository<TpvConfig, Integer> {


}
