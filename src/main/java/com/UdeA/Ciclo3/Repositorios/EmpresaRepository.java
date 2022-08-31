package com.UdeA.Ciclo3.Repositorios;


import com.UdeA.Ciclo3.Modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository //Annotation que le dice a Spring que esta class es un repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {


}
