package com.UdeA.Ciclo3.Controller;

import com.UdeA.Ciclo3.Modelos.Empleado;
import com.UdeA.Ciclo3.Modelos.Empresa;
import com.UdeA.Ciclo3.Modelos.MovimientoDinero;
import com.UdeA.Ciclo3.Service.EmpleadoService;
import com.UdeA.Ciclo3.Service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerMovimientos {

    @Autowired
    MovimientoService movimientoService;

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping({"/","/VerMovimientos"})
    public String viewMovimientos(Model model, @ModelAttribute("mensaje") String mensaje){
        List<MovimientoDinero> listaMovimientos=movimientoService.getAllMovimientos();
        model.addAttribute("movlist",listaMovimientos);
        model.addAttribute("mensaje",mensaje);
        return "verMovimientos"; //Llamamos al HTML
    }

    @GetMapping("/AgregarMovimiento")
    public String nuevoMovimiento(Model model, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero mov= new MovimientoDinero();
        model.addAttribute("mov",mov);
        model.addAttribute("mensaje",mensaje);
        List<Empleado> listaEmpleados= empleadoService.getAllEmpleado();
        model.addAttribute("emplelist",listaEmpleados);
        return "agregarMovimiento";
    }

    @PostMapping("/GuardarMovimiento")
    public String guardarMovimiento(MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movimientoService.saveOrUpdateMovimientos(mov)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarMovimiento";
    }

    @GetMapping("/EditarMovimiento/{id}")
    public String editarMovimento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero mov=movimientoService.getMovimientosByID(id);
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("mov",mov);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados= empleadoService.getAllEmpleado();
        model.addAttribute("emplelist",listaEmpleados);
        return "editarMovimiento";
    }



    @PostMapping("/ActualizarMovimiento")
    public String updateMovimiento(@ModelAttribute("mov") MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movimientoService.saveOrUpdateMovimientos(mov)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarMovimiento/"+mov.getId();

    }

    @GetMapping("/EliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (movimientoService.deleteMovimiento(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerMovimientos";
    }


    @GetMapping("/Empresa/{id}/Movimientos") //Filtrar los empleados por empresa
    public String verMovimientosPorEmpresa(@PathVariable("id") Integer id, Model model){
        List<MovimientoDinero> listaMovimientos = movimientoService.obtenerPorEmpresa(id);
        model.addAttribute("movlist",listaMovimientos);
        return "verMovimientos"; //Llamamos al html con el emplelist de los empleados filtrados
    }

    @GetMapping("/Empleado/{id}/Movimientos") //Filtrar los empleados por Empleado
    public String verMovimientosPorEmpleado(@PathVariable("id") Integer id, Model model){
        List<MovimientoDinero> listaMovimientos = movimientoService.obtenerPorEmpresa(id);
        model.addAttribute("movlist",listaMovimientos);
        return "verMovimientos"; //Llamamos al html con el emplelist de los empleados filtrados
    }
}
