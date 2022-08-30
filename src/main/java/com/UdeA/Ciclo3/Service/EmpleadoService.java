package com.UdeA.Ciclo3.Service;


import com.UdeA.Ciclo3.Modelos.Empleado;
import com.UdeA.Ciclo3.Modelos.Empresa;
import com.UdeA.Ciclo3.Repositorios.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //Le decimos al spring que es una clase de servicios
public class EmpleadoService {

    @Autowired //conectamos esta clase con el repositorio empresa
    EmpleadoRepository empleadoRepository;

    //Metodo que retornará la lista de empleados usando metodos heredados del Jparpository
    public List<Empleado> getAllEmpleado(){
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado)); // recorremos el iterable que regresa el metodo findAll
        return empleadoList;
    }

    //Metodo que trae un objeto tipo empleado cuando cuento con el id de la misma
    public Empleado getEmpleadoById (Integer id) {
        return empleadoRepository.findById(id).get();
    }

    //Metodo que guarda o actualiza emplados
    public Empleado saveOrUpdateEmpleado(Empleado empleado) {
        Empleado emp= empleadoRepository.save(empleado);
        return emp;
    }

    public boolean DeletEmpleado(Integer id) {
        empleadoRepository.deleteById(id);  //Eliminar

        if (empleadoRepository.findById(id)!=null){  //Verificacion del servicio eliminacion
            return true;
        }
        return false;

    }
}
