package com.UdeA.Ciclo3.Service;

import com.UdeA.Ciclo3.Modelos.Empleado;
import com.UdeA.Ciclo3.Modelos.MovimientoDinero;
import com.UdeA.Ciclo3.Repositorios.MovimientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    MovimientosRepository movimientosRepository;

    //Metodo que retornará la lista de movimientos usando metodos heredados del Jparpository
    public List<MovimientoDinero> getAllMovimientos(){
        List<MovimientoDinero> movimientoDineroList = new ArrayList<>();
        movimientosRepository.findAll().forEach(movimientos -> movimientoDineroList.add(movimientos));
        return  movimientoDineroList;
    }

    //Metodo que trae un objeto tipo movimientos cuando cuento con el id de la misma
    public MovimientoDinero getMovimientosByID(Integer id){
        return movimientosRepository.findById(id).get();
    }

    //Metodo para guardar o actualizar un objeto tipo empresa

    public boolean saveOrUpdateMovimientos(MovimientoDinero movimientoDinero){
        MovimientoDinero mov= movimientosRepository.save(movimientoDinero);
        if(movimientosRepository.findById(mov.getId())!=null){
            return true;
        }
        return false;
    }


    //Metodo para eliminar empresas registradas teniendo el id
    public boolean deleteMovimiento(Integer id){
        movimientosRepository.deleteById(id);  //Eliminar

        if (movimientosRepository.findById(id)!=null){  //Verificacion del servicio eliminacion
            return true;
        }
        return false;
    }

    //Metodo para buscar empleados por empresa
    public ArrayList<MovimientoDinero> obtenerPorEmpleado(Integer id) { //Obterner teniendo en cuenta el id del empleado
        return movimientosRepository.findByEmpleado(id);
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Integer id) { //Obtener movimientos teniendo en cuenta el id de la empresa a la que pertencen los empleados que la registraron
        return movimientosRepository.findByEmpresa(id);
    }

    //Servicio para ver la suma de todos los montos
    public Long obtenerSumaMontos(){
        return movimientosRepository.SumarMonto();
    }

    //Servicio para ver la suma de los montos por empleado
    public Long MontosPorEmpleado(Integer id){
        return movimientosRepository.MontosPorEmpleado(id);
    }

    //Servicio para ver la suma de los montos por empresa
    public Long MontosPorEmpresa(Integer id){
        return movimientosRepository.MontosPorEmpresa(id);
    }

}
