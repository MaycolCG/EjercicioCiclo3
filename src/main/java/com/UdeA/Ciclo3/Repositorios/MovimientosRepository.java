package com.UdeA.Ciclo3.Repositorios;

import com.UdeA.Ciclo3.Modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository //Anotacion que le dice a Spring que esta clase es un repositorio
public interface MovimientosRepository extends JpaRepository<MovimientoDinero, Integer> {

    //Consulta movimientos por empleado
    @Query(value ="select * from movimientos where empleado_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpleado(Integer id);

    // Consulta por Empresa
    @Query(value="select * from movimientos where empleado_id in(select id from empleado where empresa_id= ?1)", nativeQuery=true)
    public abstract ArrayList<MovimientoDinero> findByEmpresa(Integer id);
}