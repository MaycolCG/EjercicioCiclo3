package com.UdeA.Ciclo3.Service;


import com.UdeA.Ciclo3.Modelos.Empleado;
import com.UdeA.Ciclo3.Repositorios.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //Le decimos al spring que es una clase de servicios
public class EmpleadoService {

    @Autowired //conectamos esta clase con el repositorio empresa
    EmpleadoRepository empleadoRepository; //Instanciar

    //Metodo que retornará la lista de empleados usando metodos heredados del Jparpository
    public List<Empleado> getAllEmpleado(){
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado)); // recorremos el iterable que regresa el metodo findAll
        return empleadoList;
    }

    //Metodo que trae un objeto tipo empleado cuando cuento con el id de la misma
    public Optional<Empleado> getEmpleadoById (Integer id) { //Existe Optional
        return empleadoRepository.findById(id);
    }

    //Metodo que trae un objeto tipo empleado con el nombre de la empresa
    public ArrayList<Empleado> obtenerPorEmpresa (Integer id){
        return empleadoRepository.findByEmpresa(id);
    }

    //Metodo que guarda o actualiza emplados
    public Empleado saveOrUpdateEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public boolean DeletEmpleado(Integer id) {
        empleadoRepository.deleteById(id);  //Eliminar

        if (this.empleadoRepository.findById(id).isPresent()) {
            return false;
        }
        return true;
    }


}
