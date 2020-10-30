package com.gpo7.proceso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@SpringBootApplication
public class ProcesoApplication {

	public static void main(String[] args) {
		//BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		//System.out.println(pe.encode("flujos"));
		SpringApplication.run(ProcesoApplication.class, args);
	}

}
