package com.gpo7.proceso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.repository.UserJpaRepository;
import com.gpo7.proceso.servicio.PrivilegioService;

@RestController
@RequestMapping("/api")
public class DataRestController {

	@Autowired
	@Qualifier("privilegioServiceImpl")
	private PrivilegioService privilegioService;
	
	@Autowired
    @Qualifier("userJpaRepository")
    private UserJpaRepository userJpaRepository;
	
	@GetMapping("/rol/{idRol}/recurso/{idRecurso}/no-asignados")
	public List<Privilegio> privilegiosNoAsignadosByRecurso(@PathVariable("idRecurso") int idRecurso, @PathVariable("idRol") int idRol){
		return privilegioService.privilegiosNoAsignadosByRecurso(idRecurso, idRol);
	}
	
	@GetMapping("/rol/{idRol}/recurso/{idRecurso}/asignados")
	public List<Privilegio> privilegiosAsignadosByRecurso(@PathVariable("idRecurso") int idRecurso, @PathVariable("idRol") int idRol){
		return privilegioService.privilegiosAsignadosByRecurso(idRecurso, idRol);
	}
	
	@GetMapping("/user/{username}")
	public Usuario getUsername(@PathVariable("username") String username) {
		return userJpaRepository.findByUsername(username);
	}
}
