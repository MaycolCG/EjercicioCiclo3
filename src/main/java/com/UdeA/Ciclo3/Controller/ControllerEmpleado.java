package com.UdeA.Ciclo3.Controller;


import com.UdeA.Ciclo3.Modelos.Empleado;
import com.UdeA.Ciclo3.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerEmpleado {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/employees")
    public List<Empleado> verEmpleado(){ return empleadoService.getAllEmpleado();
    }

    @PostMapping("/employees")
    public Empleado guardarEmpleado(@RequestBody Empleado emp){
        return this.empleadoService.saveOrUpdateEmpleado(emp);
    }

    @GetMapping(path = "employees/{id}")
    public Empleado empleadoPorID(@PathVariable("id") Integer id){
        return this.empleadoService.getEmpleadoById(id);
    }

    @PatchMapping("/employees/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody Empleado empleado) {
        Empleado emp = empleadoService.getEmpleadoById(id);
        emp.setNombre(empleado.getNombre());
        emp.setCorreo(empleado.getCorreo());
        emp.setEmpresa(empleado.getEmpresa());
        emp.setRol(empleado.getRol());
        return this.empleadoService.saveOrUpdateEmpleado(emp);
    }

    @DeleteMapping (path= "employees/{id}") //Eliminar registro de la bd
    public String DeleteEmpleado(@PathVariable("id") Integer id){
        boolean respuesta= this.empleadoService.DeletEmpleado(id);
        if (respuesta){  //Si respuesta es true?
            return "Se elimino registro de empleado con id " +id;
        }
        else{
            return "No se pudo eliminar registro de empleado con id " +id;
        }
    }


}
