package com.gpo7.proceso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpo7.proceso.entity.Permiso;
import com.gpo7.proceso.servicio.PermisoService;

@Controller
@RequestMapping("/permiso")
public class PermisoController {
	
	private static final String INDEX_VIEW = "permiso/index";
	
	
	@Autowired
	@Qualifier("permisoServiceImpl")
	private PermisoService permisoService;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView(INDEX_VIEW);
		
		mav.addObject("permisos", permisoService.getAll());
		mav.addObject("permiso", new Permiso());
		
		return mav;
	}
	
	@PostMapping("/store")
	public String store(@ModelAttribute("permiso")Permiso permiso) {
		
		permisoService.store(permiso);
		
		return "redirect:/permiso/index";
	}
}
