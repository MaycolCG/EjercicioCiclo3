package com.UdeA.Ciclo3.Repositorios;

import com.UdeA.Ciclo3.Modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Anotacion que le dice a Spring que esta clase es un repositorio
public interface MovimientosRepository extends JpaRepository<MovimientoDinero, Integer> {
}
