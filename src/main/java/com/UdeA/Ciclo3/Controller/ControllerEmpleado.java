package com.UdeA.Ciclo3.Controller;


import com.UdeA.Ciclo3.Modelos.Empleado;
import com.UdeA.Ciclo3.Service.EmpleadoService;
import com.UdeA.Ciclo3.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ControllerEmpleado {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/employees")
    public List<Empleado> verEmpleado(){ //Ver Json de todos los empleados
        return empleadoService.getAllEmpleado();
    }

    @PostMapping("/employees")
    //Guardar empleados
    public Optional<Empleado> guardarEmpleado(@RequestBody Empleado emp){
        return Optional.ofNullable(this.empleadoService.saveOrUpdateEmpleado(emp));
    }

    @GetMapping(path = "employees/{id}")
    //Buscar Empleados
    public Optional<Empleado> empleadoPorID(@PathVariable("id") Integer id){
        return empleadoService.getEmpleadoById(id);
    }

    @GetMapping("/enterprises/{id}/employees")
    public ArrayList<Empleado> empleadoPorEmpresa (@PathVariable ("id") Integer id){
        return empleadoService.obtenerPorEmpresa(id);
    }


   @PatchMapping("/employees/{id}")
    //Actualizar Empleados
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody Empleado empleado) {
        Empleado emp = empleadoService.getEmpleadoById(id).get(); //Buscarlo
        emp.setNombre(empleado.getNombre());
        emp.setCorreo(empleado.getCorreo());
        emp.setEmpresa(empleado.getEmpresa());
        emp.setRol(empleado.getRol());
        return empleadoService.saveOrUpdateEmpleado(emp);
    }

    @DeleteMapping (path= "employees/{id}") //Eliminar registro de la bd
    public String DeleteEmpleado(@PathVariable("id") Integer id){
        boolean respuesta= empleadoService.DeletEmpleado(id); // Eliminamos usando el servicio de nuestro service
        if (respuesta){  //Si respuesta es true?
            return "Se elimino registro de empleado con id " +id;
        }
        else{
            return "No se pudo eliminar registro de empleado con id " +id;
        }
    }


}
