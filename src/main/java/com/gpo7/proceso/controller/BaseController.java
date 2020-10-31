package com.gpo7.proceso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.repository.UserJpaRepository;

@Controller
@RequestMapping("/")
public class BaseController {
	
	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/base")
	public String base() {
		return "base";
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);

		return "login";
	}
	
	@GetMapping({"/loginsuccess","/"})
	public String loginCheck() {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario  = userJpaRepository.findByUsername(user.getUsername());
		usuario.setIntentos(0);
		userJpaRepository.save(usuario);
		
		return "redirect:/index";
	}

}
