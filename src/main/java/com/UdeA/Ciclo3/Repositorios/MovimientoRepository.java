package com.UdeA.Ciclo3.Repositorios;

import com.UdeA.Ciclo3.Modelos.Empleado;
import com.UdeA.Ciclo3.Modelos.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimientos, Integer> {

    //Consulta movimientos por empleado
    @Query(value ="select * from movimientos where empleado_id= ?1", nativeQuery = true)
    public abstract ArrayList<Movimientos> findByEmpleado(Integer id);

    // Consulta por Empresa
    @Query(value="SELECT * from movimientos where empleado_1 in(select id from empleado where empresa_id= ?1)", nativeQuery=true)
    public abstract ArrayList<Movimientos> findByEmpresa(Integer id);
}
