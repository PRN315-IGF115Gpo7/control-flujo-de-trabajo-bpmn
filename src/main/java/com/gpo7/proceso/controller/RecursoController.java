package com.gpo7.proceso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	private static final String CREATE_VIEW = "recurso/create";
	private static final String EDIT_VIEW = "recurso/edit";
	
	@Autowired
	@Qualifier("recursoServiceImpl")
	private RecursoService recursoService;
	
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView(INDEX_VIEW);
		
		
		List<Recurso> recursos = recursoService.getAll();  
		
		mav.addObject("recursos", recursos);
		
		return mav;
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView(CREATE_VIEW);
		mav.addObject("newRecurso", new Recurso());
		return mav;
	}
	
	
	@PostMapping("/store")
	public String store(@ModelAttribute("newRecurso") Recurso recurso ) {
		recursoService.store(recurso);
		return "redirect:/recurso/index";
	}
	//falta el view de editar
	@GetMapping("/edit/id_recurso")
	public ModelAndView edit(@PathVariable int id_recurso) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW);
		Recurso recurso = recursoService.findById(id_recurso);
		mav.addObject("recurso", recurso);
		return mav;
		
	}
	
	@PostMapping("/destroy")
	public String destroy(@RequestParam("idRecurso") int id_recurso) {
		Recurso recurso = recursoService.findById(id_recurso);
		
		if(recurso.getRolesRecursosPrivilegios().isEmpty()) {
			recursoService.destroy(recurso);
<<<<<<< HEAD
		
	}
		return "redirect:/recurso/index";
=======
		}
		return "redirect:/recurso/index";

>>>>>>> d49fc149dd280684b4a9e2834549a24ec25eebdb
	}
}
