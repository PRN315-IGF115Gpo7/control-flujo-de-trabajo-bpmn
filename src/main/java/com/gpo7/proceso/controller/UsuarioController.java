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

import com.gpo7.proceso.entity.Rol;
import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.servicio.UsuarioService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

private static final String INDEX_VIEW="usuario/index";
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioService;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav=new ModelAndView(INDEX_VIEW);
		List<Usuario> usuarios=usuarioService.getAll();
		mav.addObject("usuarios", usuarios );
		return mav;
	}
	@PostMapping("/update-baja")
    public String updateBaja() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Usuario usuarioModificado= usuarioService.findByUsername(user.getUsername());
    	
    	usuarioModificado.setEnabled(false);
    	
    	
    	usuarioService.update(usuarioModificado);
    	
    	return "redirect:/login";
    }

	@PostMapping("/store")
    public String store(@Valid @ModelAttribute("usuario")Usuario usuario, BindingResult results, RedirectAttributes redirAttrs){
		if(results.hasErrors()) {
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/usuario/index";
		}
    	usuarioService.store(usuario);
		redirAttrs.addFlashAttribute("success", "El usuario fue registrado con éxito");
    	
    	return "redirect:/usuario/index";
    }
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult results, RedirectAttributes redirAttrs) {
		Usuario usuarioModificado = usuarioService.findById(usuario.getIdUsuario());
		
		usuarioModificado.setUsername(usuario.getUsername());
		
		if(results.hasErrors()) {
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/usuario/index";
		}
		
		usuarioService.update(usuarioModificado);
		
		redirAttrs.addFlashAttribute("success", "El usuario fue modificado con éxito");
		
		return "redirect:/usuario/index";
	}


@PostMapping("/destroy")
public String destroy(@RequestParam("id_user") int idUser) {
	
	Usuario usuario = usuarioService.findById(idUser);
    usuario.setEnabled(false);
		
	usuarioService.update(usuario);
	
	return "redirect:/usuario/index";
}
	
	
}
