package com.gpo7.proceso.controller;

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

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.servicio.CargoService;

@Controller
@RequestMapping("/cargo")
public class CargoController {
	
	private static final String INDEX_VIEW = "cargo/index";
	
	@Autowired
	@Qualifier("cargoServiceImpl")
	private CargoService cargoService;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView(INDEX_VIEW);
		
		mav.addObject("cargos", cargoService.getAll());
		mav.addObject("cargo", new Cargo());
		
		return mav;
	}
	@PostMapping("/store")
	public String store(@Valid @ModelAttribute("cargo") Cargo cargo, BindingResult results, RedirectAttributes redirAttrs) {
		
		if(results.hasErrors()) {
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/cargo/index";
		}
		
		cargoService.store(cargo);
		redirAttrs.addFlashAttribute("success", "El cargo fue registrado con éxito");
		
		return "redirect:/cargo/index";
	}
	
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("cargo") Cargo cargo, BindingResult results, RedirectAttributes redirAttrs) {
		Cargo cargoModificado = cargoService.findById(cargo.getIdCargo());
		
		cargoModificado.setNombreCargo(cargo.getNombreCargo());
		cargoModificado.setDescripcionCargo(cargo.getDescripcionCargo());
		
		if(results.hasErrors()) {
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/cargo/index";
		}
		cargoService.update(cargoModificado);
		redirAttrs.addFlashAttribute("success", "El cargo fue modificado con éxito");
		
		return "redirect:/cargo/index";
	}
	@PostMapping("/destroy")
	public String destroy(@RequestParam("idCargo") int id_cargo, RedirectAttributes redirAttrs) {
		Cargo cargo = cargoService.findById(id_cargo);
		
		if(cargo.getUsuarios().isEmpty()) {
			cargoService.destroy(cargo);
		}else {
			redirAttrs.addFlashAttribute("mensaje", "Este cargo no puede eliminado porque ya fue asignado");
		}
		
		return "redirect:/cargo/index";
	}
	

}
