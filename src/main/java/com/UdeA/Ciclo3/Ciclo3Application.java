package com.UdeA.Ciclo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication (exclude ={SecurityAutoConfiguration.class})
@RestController
public class Ciclo3Application {

	@GetMapping("/hello")
	public String hello(){
		return "Hola ciclo 3";
	}

	public static void main(String[] args) {
		SpringApplication.run(Ciclo3Application.class, args);

		System.out.println("Hola Mundo");
	}
}
