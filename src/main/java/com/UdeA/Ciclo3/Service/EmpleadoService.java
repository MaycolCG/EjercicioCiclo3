package com.UdeA.Ciclo3.Service;

import com.UdeA.Ciclo3.Modelos.Empleado;
import com.UdeA.Ciclo3.Repositorios.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // Indicamos que es una clase service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository; //conectamos esta clase con el repositorio empleado

    //Metodo que retornará la lista de empleados usando metodos heredados del Jparpository
    public List<Empleado> getAllEmpleados(){
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado));

        return empleadoList;
    }

    //Metodo que trae un objeto tipo empleado cuando cuento con el id del mismo
    public Empleado getEmpleadoByID(Integer id){
        return empleadoRepository.findById(id).get();
    }

    //Metodo para guardar o actualizar un objeto tipo empleado

    public boolean saveOrUpdateEmpleado(Empleado empleado){
        Empleado emp = empleadoRepository.save(empleado);
        if(empleadoRepository.findById(emp.getId())!= null){
            return true;
        }
        return false;
    }

    //Metodo para eliminar empleados registrados teniendo el id
    public boolean deleteEmpleado(Integer id){
        empleadoRepository.deleteById(id);  //Eliminar

        if (empleadoRepository.findById(id)!=null){  //Verificacion del servicio eliminacion
            return true;
        }
        return false;
    }


}
