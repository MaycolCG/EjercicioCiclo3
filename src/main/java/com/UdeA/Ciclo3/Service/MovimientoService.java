package com.UdeA.Ciclo3.Service;


import com.UdeA.Ciclo3.Modelos.Movimientos;
import com.UdeA.Ciclo3.Repositorios.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;

    //Metodo que retornará la lista de movimientos usando metodos heredados del Jparepository
    public List<Movimientos> getAllMovimientos() {
        List<Movimientos> movimientoList = new ArrayList<>();
        movimientoRepository.findAll().forEach(movimiento -> movimientoList.add(movimiento));
        return movimientoList;
    }

    //Metodo que trae un objeto tipo movimiento cuando cuento con el id de la misma
    public Movimientos getMovimientoById(Integer id) {
        return movimientoRepository.findById(id).get();
    }

    //Metodo para guardar o actualizar un objeto tipo empresa
    public Movimientos saveOrUpdateMovimientos(Movimientos movimientos){
        Movimientos mov = movimientoRepository.save(movimientos);
        return mov;
    }

    //Metodo para eliminar empresas registradas teniendo el id
    @DeleteMapping("/empleados/{id}") //Metodo para eliminar empleados por id
    public boolean deleteMovimiento(Integer id){ //Eliminar movimiento por id
        movimientoRepository.deleteById(id); //Eliminar usando el metodo que nos ofrece el repositorio
        if(movimientoRepository.findById(id).isPresent()){ //Si al buscar el movimiento lo encontramos, no se eliminó (false)
            return false;
        }
        return true; //Si al buscar el movimiento no lo encontramos, si lo eliminò (true)
    }

    public ArrayList<Movimientos> obtenerPorEmpleado(Integer id){//Filtrar movimientos por empleados
        return movimientoRepository.findByEmpleado(id);
    }

    public ArrayList<Movimientos> obtenerPorEmpresa(Integer id){//Filtrar movimientos por empresa
        return movimientoRepository.findByEmpresa(id);
    }

}
