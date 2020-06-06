package ar.com.bambu.repos;


import ar.com.bambu.entities.FactuMem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FactuMemRepository extends JpaRepository<FactuMem, Integer> {

    @Query(value = "select new ar.com.bambu.entities.FactuMem (fm.valor ) from FactuMem fm  where fm.id=:id")
    FactuMem findById(@Param("id") int id);
}
