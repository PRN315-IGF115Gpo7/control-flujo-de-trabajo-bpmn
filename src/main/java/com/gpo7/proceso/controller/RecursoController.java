package com.gpo7.proceso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gpo7.proceso.entity.Recurso;
import com.gpo7.proceso.servicio.RecursoService;


@Controller
@RequestMapping("/recurso")
public class RecursoController {
	
	private static final String INDEX_VIEW = "recurso/index";
	
	
	@Autowired
	@Qualifier("recursoServiceImpl")
	private RecursoService recursoService;
	
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView(INDEX_VIEW);
		mav.addObject("recursos", recursoService.getAll());
		mav.addObject("newRecurso", new Recurso()); 
				
		return mav;
	}	
	
	@PostMapping("/store")
	public String store(@ModelAttribute("newRecurso") Recurso recurso ) {
		recursoService.store(recurso);
		return "redirect:/recurso/index";
	}
	@PostMapping("/update")
	public String update(@ModelAttribute("newRecurso") Recurso recurso) {
		Recurso recursoMod = recursoService.findById(recurso.getIdRecurso());
		recursoMod.setRecurso(recurso.getRecurso());
		recursoService.update(recursoMod);
		
		return "redirect:/recurso/index";
		
	}
	
	@PostMapping("/destroy")
	public String destroy(@RequestParam("idRecurso") int id_recurso) {
		Recurso recurso = recursoService.findById(id_recurso);
		
		if(recurso.getRolesRecursosPrivilegios().isEmpty()) {
			recursoService.destroy(recurso);		
	}
		return "redirect:/recurso/index";

	}
}
