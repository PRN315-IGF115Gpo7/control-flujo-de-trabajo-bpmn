package com.gpo7.proceso.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.Rol;
import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.servicio.CargoService;
import com.gpo7.proceso.servicio.RolService;
import com.gpo7.proceso.servicio.UsuarioService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private static final String INDEX_VIEW="usuario/index";
	private static final String CREATE_VIEW="usuario/create";
	private static final String EDIT_VIEW="usuario/edit";
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("cargoServiceImpl")
	private CargoService cargoService;
	
	@Autowired
	@Qualifier("rolServiceImpl")
	private RolService rolService;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav=new ModelAndView(INDEX_VIEW);
		List<Usuario> usuarios=usuarioService.getAll();
		List<Cargo> cargos = cargoService.getAll();
		
		mav.addObject("usuarios", usuarios );
		mav.addObject("cargos", cargos);
		mav.addObject("usuario", new Usuario() );
		
		return mav;
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView(CREATE_VIEW);
		List<Cargo> cargos = cargoService.getAll();
		
		mav.addObject("usuario", new Usuario());
		mav.addObject("cargos", cargos);
		mav.addObject("roles", rolService.getAll());
		
		return mav;
	}

	@PostMapping("/store")
    public String store(@Valid @ModelAttribute("usuario")Usuario usuario, 
    		BindingResult results, @RequestParam("idCargo") Integer idCargo, 
    		RedirectAttributes redirAttrs, @RequestParam(name = "rolesAsig[]", required = false) List<Integer> roles){
		
		Usuario usuarioEmail = usuarioService.findByEmail(usuario.getEmail());
		Usuario usuarioUsername = usuarioService.findByUsername(usuario.getUsername());
				
		if(results.hasErrors() || usuarioEmail != null || usuarioUsername != null) {
			if(usuarioEmail != null) {
				results.reject("email", "El nombre de usuario debe ser único.");
			}
			if(usuarioUsername != null) {
				results.reject("username", "El email debe ser único.");
			}
			
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/usuario/create";
		}
		
		Cargo cargo = cargoService.findById(idCargo);
		
		List<Rol> rolesList = new ArrayList<Rol>();
        if(roles != null) {
        	for (int idRol: roles) {
            	Rol rol = rolService.findById(idRol);
            	rolesList.add(rol);
            }
        }
        
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        usuario.setPassword(pe.encode("flujos")); 
        
        usuario.setRoles(rolesList);
		usuario.setCargo(cargo);
		usuario.setEnabled(true);
		
    	usuarioService.store(usuario);
		redirAttrs.addFlashAttribute("success", "El usuario fue registrado con éxito");
    	
    	return "redirect:/usuario/index";
    }
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer idUsuario) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW);
		List<Cargo> cargos = cargoService.getAll();
		
		Usuario usuario = usuarioService.findById(idUsuario);
		
		mav.addObject("usuario", usuario);
		mav.addObject("cargos", cargos);
		mav.addObject("availableRoles", rolService.getAvailableRoles(idUsuario));
		mav.addObject("userRoles", rolService.getUserRoles(idUsuario));
		
		return mav;
	}
	
	@PostMapping("/update")
    public String update(@Valid @ModelAttribute("usuario")Usuario usuario, 
    		BindingResult results, @RequestParam("idCargo") Integer idCargo, 
    		RedirectAttributes redirAttrs, @RequestParam(name = "rolesAsig[]", required = false) List<Integer> roles){
		
		Usuario usuarioEmail = usuarioService.findByEmail(usuario.getEmail());
		Usuario usuarioUsername = usuarioService.findByUsername(usuario.getUsername());
		Usuario usuarioMod = usuarioService.findById(usuario.getIdUsuario());
		
		if(usuarioEmail.getIdUsuario() == usuario.getIdUsuario()) {
			usuarioEmail = null;
		}
		
		if(usuarioUsername.getIdUsuario() == usuario.getIdUsuario()) {
			usuarioUsername = null;
		}
		
		if(results.hasErrors() || usuarioEmail != null || usuarioUsername != null) {
			if(usuarioUsername != null) {
				results.reject("email", "El nombre de usuario debe ser único.");
			}
			if(usuarioEmail!= null) {
				results.reject("username", "El email debe ser único.");
			}
			
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/usuario/edit/" + usuario.getIdUsuario();
		}
		
		Cargo cargo = cargoService.findById(idCargo);
		List<Rol> rolesList = new ArrayList<Rol>();
        if(roles != null) {
        	for (int idRol: roles) {
            	Rol rol = rolService.findById(idRol);
            	rolesList.add(rol);
            }
        }
        usuarioMod.setRoles(rolesList);
        usuarioMod.setCargo(cargo);
        usuarioMod.setNombreCompleto(usuario.getNombreCompleto());
        usuarioMod.setUsername(usuario.getUsername());
        usuarioMod.setEmail(usuario.getEmail());
		
    	usuarioService.update(usuarioMod);
		redirAttrs.addFlashAttribute("success", "El usuario fue actualizado con éxito");
    	
    	return "redirect:/usuario/index";
    }
	
	@PostMapping("/destroy")
	public String destroy(@RequestParam("id_user") int idUser, RedirectAttributes redirAttrs) {
		
		Usuario usuario = usuarioService.findById(idUser);
		usuario.setEnabled(!usuario.isEnabled());
		String mensaje = "";
		if(usuario.isEnabled()) {
			mensaje = "habilitado";
		}else {
			mensaje = "deshabilitado";
		}
		
		usuarioService.update(usuario);
		
		redirAttrs.addFlashAttribute("success", "El usuario " + usuario.getUsername() + " fue " + mensaje + " con éxito");
		
		return "redirect:/usuario/index";
	}
	
	@PostMapping("/update-baja")
    public String updateBaja() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Usuario usuarioModificado= usuarioService.findByUsername(user.getUsername());
    	
    	usuarioModificado.setEnabled(false);
    	
    	
    	usuarioService.update(usuarioModificado);
    	
    	return "redirect:/login";
    }
	
	
}
