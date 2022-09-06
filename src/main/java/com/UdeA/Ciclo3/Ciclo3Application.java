package com.UdeA.Ciclo3;

import com.UdeA.Ciclo3.Modelos.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@SpringBootApplication (exclude ={SecurityAutoConfiguration.class})
public class Ciclo3Application {

	@GetMapping({"/Home"})
	public String Hola(){
		return "Home";
	}

	@GetMapping("/test")
	public String test(){
		Empresa emp = new Empresa("SOLAR SAS", "Calle la geta", "3213213211","800212132-3");
		emp.setNombre("SOLAR LTDA");
		return emp.getNombre();
	}

	public static void main(String[] args) {
		SpringApplication.run(Ciclo3Application.class, args);
	}

}
