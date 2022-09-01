package com.UdeA.Ciclo3.Controller;

import com.UdeA.Ciclo3.Modelos.Empresa;
import com.UdeA.Ciclo3.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ControllerEmpresa {

    @Autowired
    EmpresaService empresaService;

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresa(){ //Listado de empresas
        return empresaService.getAllEmpresas();
    }

    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa emp){ //Guardar empresas
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @GetMapping(path = "enterprises/{id}") //Buscar empresa pòr id
    public Empresa empresaPorID(@PathVariable("id") Integer id){
        return this.empresaService.getEmpresaById(id);
    }

    @PatchMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa) {
        Empresa emp = empresaService.getEmpresaById(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNIT(empresa.getNIT());
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @DeleteMapping (path= "enterprises/{id}") //Eliminar registro de la bd
    public String DeleteEmpresa(@PathVariable("id") Integer id){
        boolean respuesta= this.empresaService.deleteEmpresa(id);
        if (respuesta){  //Si respuesta es true?
            return "Se elimino la empresa con id " +id;
        }
        else{
            return "No se pudo eliminar la empresa con id "+id;
        }
    }
}