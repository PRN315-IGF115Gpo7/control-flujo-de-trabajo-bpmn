package com.gpo7.proceso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public String store(@ModelAttribute("usuario")Usuario usuario){
    	
    	usuarioService.store(usuario);
    	
    	return "redirect:/usuario/index";
    }
	
}
