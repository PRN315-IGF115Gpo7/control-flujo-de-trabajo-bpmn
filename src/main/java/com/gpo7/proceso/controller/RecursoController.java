package com.gpo7.proceso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gpo7.proceso.entity.Recurso;
import com.gpo7.proceso.servicio.RecursoService;

@Controller
@RequestMapping("/recurso")
public class RecursoController {

	private static final String INDEX_VIEW = "recurso/index";

	@Autowired
	@Qualifier("recursoServiceImpl")
	private RecursoService recursoService;

	@PreAuthorize("hasAuthority('RECURSO_INDEX')")
	@GetMapping({ "/index", "" })
	public ModelAndView index(@RequestParam(name = "store_success", required = false) String store_success,
			@RequestParam(name = "update_success", required = false) String update_success,
			@RequestParam(name = "delete_success", required = false) String delete_success) {
		ModelAndView mav = new ModelAndView(INDEX_VIEW);
		mav.addObject("recursos", recursoService.getAll());
		mav.addObject("newRecurso", new Recurso());
		mav.addObject("store_success", store_success);
		mav.addObject("update_success", update_success);
		mav.addObject("delete_success", delete_success);

		return mav;
	}

	@PreAuthorize("hasAuthority('RECURSO_CREATE')")
	@PostMapping("/store")
	public String store(@ModelAttribute("newRecurso") Recurso recurso, RedirectAttributes redirAttrs) {
		recursoService.store(recurso);
		redirAttrs.addFlashAttribute("success", "El recurso fue almacenado con éxito");
		
		return "redirect:/recurso/index?store_success";
	}

	@PreAuthorize("hasAuthority('RECURSO_UPDATE')")
	@PostMapping("/update")
	public String update(@ModelAttribute("newRecurso") Recurso recurso, RedirectAttributes redirAttrs) {
		Recurso recursoMod = recursoService.findById(recurso.getIdRecurso());
		recursoMod.setRecurso(recurso.getRecurso());
		recursoService.update(recursoMod);
		
		redirAttrs.addFlashAttribute("success", "El recurso fue actualizado con éxito");

		return "redirect:/recurso/index?update_success";

	}

	@PreAuthorize("hasAuthority('RECURSO_DELETE')")
	@PostMapping("/destroy")
	public String destroy(@RequestParam("idRecurso") int id_recurso, RedirectAttributes redirAttrs) {
		Recurso recurso = recursoService.findById(id_recurso);

		if (recurso.getRolesRecursosPrivilegios().isEmpty()) {
			recursoService.destroy(recurso);
			redirAttrs.addFlashAttribute("success", "El recurso fue eliminado con éxito");
		}else {
			redirAttrs.addFlashAttribute("error", "Este recurso no puede ser eliminado porque ya fue asignado");
		}
		
		return "redirect:/recurso/index";

	}
}
