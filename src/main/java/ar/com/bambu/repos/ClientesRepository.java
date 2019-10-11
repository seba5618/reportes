package ar.com.bambu.repos;

import ar.com.bambu.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {


    @Query(value = "select new ar.com.bambu.entities.Clientes (cl, ci.descCondIva as descripcion) from Clientes cl join CondicionesIva ci on cl.condIva=ci.codCondIva where cl.codCliente=:codCliente")
    Clientes findByCodClienteConCondicionIva(@Param("codCliente") int codCliente);
}
