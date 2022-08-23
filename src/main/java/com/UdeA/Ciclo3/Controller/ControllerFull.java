package com.UdeA.Ciclo3.Controller;

import com.UdeA.Ciclo3.Modelos.Empresa;
import com.UdeA.Ciclo3.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ControllerFull {
    @Autowired
    EmpresaService empresaService;

    @GetMapping ({"/","/VerEmpresas"})
    public String viewEmpresas(Model model){
        List<Empresa> listaEmpresas=empresaService.getAllEmpresas();
        model.addAttribute("emplist",listaEmpresas);
        return "verEmpresas";
    }

}
