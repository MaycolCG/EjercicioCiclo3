package com.UdeA.Ciclo3.Service;

import com.UdeA.Ciclo3.Modelos.Empresa;
import com.UdeA.Ciclo3.Repositorios.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Le decimos al spring que es una clase de servicios
@Service
public class EmpresaService {
    @Autowired //conectamos esta clase con el repositorio empresa
    EmpresaRepository empresaRepository;

    //Metodo que retornará la lista de empresas usando metodos heredados del Jparpository
    public List<Empresa> getAllEmpresas(){
        List<Empresa> empresaList= new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa)); // recorremos el iterable que regresa el metodo findAll
        return empresaList;
    }

    //Metodo que trae un objeto tipo empresa cuando cuernto con el id de la misma
    public Empresa getEmpresaById (Integer id){
        return empresaRepository.findById(id).get();
    }

    //Metodo para guardar o actualizar un objeto tipo empresa
    public boolean saveOrUpdateEmpresa(Empresa empresa){
        Empresa emp= empresaRepository.save(empresa);
        if(empresaRepository.findById(emp.getId())!= null){
            return true;
        }
        return false;
    }

    //Metodo para eliminar empresas registradas teniendo el id

    public boolean deleteEmpresa(Integer id){
        empresaRepository.deleteById(id);
        if (getEmpresaById(id)!=null){
            return false;
        }
        return true;
    }

}
