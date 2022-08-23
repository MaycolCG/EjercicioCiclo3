package com.UdeA.Ciclo3.Repositorios;

import com.UdeA.Ciclo3.Modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Anotación que indica que esta clase es un Repositorio
public interface EmpresaRepository extends JpaRepository <Empresa, Integer>{

}
