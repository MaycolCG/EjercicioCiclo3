package com.UdeA.Ciclo3.Controller;

import com.UdeA.Ciclo3.Modelos.Empleado;
import com.UdeA.Ciclo3.Modelos.Movimientos;
import com.UdeA.Ciclo3.Service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerMovimientos {

    @Autowired
    MovimientoService movimientoService;

    @GetMapping ("/transaction")
    public List<Movimientos> verMovimientos(){ //Listar o consultar movimientos
        return movimientoService.getAllMovimientos();
    }

    @PostMapping("/transaction")
    public Movimientos guardarMovimientos (@RequestBody Movimientos mov){//Guardar movimientos
        return movimientoService.saveOrUpdateMovimientos(mov);
    }

    @GetMapping("/transaction/{id}")
    public Movimientos movimientoPorID(@PathVariable ("id") Integer id){//Consulta movimientos con el Id del empleado
        return movimientoService.getMovimientoById(id);
    }

    @PatchMapping("/transaction/{id}")
    public Movimientos actualizarMovimiento(@PathVariable("id") Integer id, @RequestBody Movimientos movimientos) {
        Movimientos mov = movimientoService.getMovimientoById(id); //Buscarlo
        mov.setConcepto(movimientos.getConcepto());
        mov.setMonto(movimientos.getMonto());
        mov.setUsuario(movimientos.getUsuario());
        return movimientoService.saveOrUpdateMovimientos(mov);
    }

    @DeleteMapping (path= "/transaction/{id}") //Eliminar registro de la bd
    public String DeleteMovimiento(@PathVariable("id") Integer id){
        boolean respuesta= movimientoService.deleteMovimiento(id); // Eliminamos usando el servicio de nuestro service
        if (respuesta){  //Si respuesta es true?
            return "Se elimino registro de Movimiento con id " +id;
        }
        else{
            return "No se pudo eliminar registro de Movimiento con id " +id;
        }
    }

    @GetMapping("/employees/{id}/transaction") //Consultar movimientos por id del empleado
    public ArrayList<Movimientos> movimientosPorEmpleado(@PathVariable("id") Integer id){
        return movimientoService.obtenerPorEmpleado(id);
    }

    @GetMapping("/enterprise/{id}/transaction")//Consultar movimientos que pertenecen a una empresa por el id de la empresa
    public ArrayList<Movimientos> movimientosPorEmpresa(@PathVariable("id") Integer id){
        return movimientoService.obtenerPorEmpresa(id);
    }


}
