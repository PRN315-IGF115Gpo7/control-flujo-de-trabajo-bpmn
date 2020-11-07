package com.gpo7.proceso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gpo7.proceso.servicio.RolService;
import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.Rol;


@Controller
@RequestMapping("/rol")
public class RolController {
	private static final String INDEX_VIEW="rol/index";
	
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
    public String store(@Valid @ModelAttribute("rol")Rol rol, BindingResult results, RedirectAttributes redirAttrs){
		if(results.hasErrors()) {
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/rol/index";
		}
    	rolService.store(rol);
		redirAttrs.addFlashAttribute("success", "El rol fue registrado con éxito");
    	
    	return "redirect:/rol/index";
    }
    
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("rol") Rol rol, BindingResult results, RedirectAttributes redirAttrs) {
		Rol rolModificado = rolService.findById(rol.getIdRol());
		
		rolModificado.setAuthority(rol.getAuthority());
		
		if(results.hasErrors()) {
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/rol/index";
		}
		
		rolService.update(rolModificado);
		
		redirAttrs.addFlashAttribute("success", "El rol fue modificado con éxito");
		
		return "redirect:/rol/index";
	}

@PostMapping("/destroy")
public String destroy(@RequestParam("id_rol") int idRol, RedirectAttributes redirAttrs) {
	Rol rol = rolService.findById(idRol);
	
	if(rol.getUsuarios().isEmpty() || rol.getRolesRecursosPrivilegios().isEmpty()) {
		rolService.destroy(rol);
	}else {
		redirAttrs.addFlashAttribute("mensaje", "Este rol no puede ser eliminado porque ya fue asignado");
	}
	
	return "redirect:/rol/index";
}
}
