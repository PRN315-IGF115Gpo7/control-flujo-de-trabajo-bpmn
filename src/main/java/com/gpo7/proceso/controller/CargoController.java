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
	public String store(@ModelAttribute("cargo") Cargo cargo) {
		
		cargoService.store(cargo);
		
		return "redirect:/cargo/index";
	}
	@PostMapping("/update")
	public String update(@ModelAttribute("cargo")Cargo cargo) {
		Cargo cargoModificado = cargoService.findById(cargo.getIdCargo());
		
		cargoModificado.setNombreCargo(cargo.getNombreCargo());
		cargoModificado.setDescripcionCargo(cargo.getDescripcionCargo());
		
		cargoService.update(cargoModificado);
		
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
