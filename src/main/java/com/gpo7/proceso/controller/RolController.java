package com.gpo7.proceso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpo7.proceso.servicio.RolService;

import com.gpo7.proceso.entity.Rol;


@Controller
@RequestMapping("/rol")
public class RolController {
	private static final String INDEX_VIEW="rol/index";
	private static final String CREATE_VIEW="rol/create";
	
	@Autowired
	@Qualifier("rolServiceImpl")
	private RolService rolService;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav=new ModelAndView(INDEX_VIEW);
		List<Rol> roles=rolService.getAll();
		mav.addObject("roles", roles );
		mav.addObject("rol", new Rol());
		return mav;
	}
	
	
	@PostMapping("/store")
    public String store(@ModelAttribute("rol")Rol rol){
    	
    	rolService.store(rol);
    	
    	return "redirect:/rol/index";
    }
    
	
}
