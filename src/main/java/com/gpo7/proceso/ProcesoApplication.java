package com.gpo7.proceso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class ProcesoApplication {

	public static void main(String[] args) {
		//BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		//System.out.println(pe.encode("flujos"));
		SpringApplication.run(ProcesoApplication.class, args);
	}

}
