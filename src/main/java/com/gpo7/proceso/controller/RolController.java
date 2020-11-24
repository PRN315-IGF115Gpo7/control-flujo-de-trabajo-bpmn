package com.gpo7.proceso.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.gpo7.proceso.servicio.PrivilegioService;
import com.gpo7.proceso.servicio.RecursoService;
import com.gpo7.proceso.servicio.RolRecursoPrivilegioService;
import com.gpo7.proceso.servicio.RolService;

import objects.RolePrivilege;
import objects.RoleResource;

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Recurso;
import com.gpo7.proceso.entity.Rol;
import com.gpo7.proceso.entity.RolRecursoPrivilegio;


@Controller
@RequestMapping("/rol")
public class RolController {
	private static final String INDEX_VIEW="rol/index";
	private static final String ASSIGN_VIEW="rol/asignacion";
	private static final String LOAD_PRIVILEGES="rol/load_privileges";
	
	@Autowired
	@Qualifier("rolServiceImpl")
	private RolService rolService;
	
	@Autowired
	@Qualifier("recursoServiceImpl")
	private RecursoService recursoService;
	
	@Autowired
	@Qualifier("privilegioServiceImpl")
	private PrivilegioService privilegioService;
	
	@Autowired
	@Qualifier("rolRecursoPrivilegioServiceImpl")
	private RolRecursoPrivilegioService rolRecursoPrivilegioService;
	
	@GetMapping({"/index", ""})
	public ModelAndView index(@RequestParam(name="delete_success", required=false) String delete_success) {
		ModelAndView mav=new ModelAndView(INDEX_VIEW);
		List<Rol> roles=rolService.getAll();
		mav.addObject("roles", roles );
		mav.addObject("rol", new Rol());
		mav.addObject("delete_success", delete_success);
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
		
		return "redirect:/rol/index?delete_success";
	}
	
	@GetMapping("/asignar/{idRol}")
	public ModelAndView asignarRrecursoPrivilegio(@PathVariable("idRol") Integer idRol) {
		ModelAndView mav = new ModelAndView(ASSIGN_VIEW);
		List<RoleResource> rolesRes = new ArrayList<RoleResource>();
		
		List<Recurso> recursos = recursoService.getAll();
		List<Privilegio> privilegiosAsignados = new ArrayList<Privilegio>();
		List<Privilegio> privilegiosNoAsignados	= new ArrayList<Privilegio>();	
		
		if(!recursos.isEmpty()) {
			privilegiosAsignados = privilegioService.privilegiosAsignadosByRecurso(recursos.get(0).getIdRecurso(), idRol);
			privilegiosNoAsignados = privilegioService.privilegiosNoAsignadosByRecurso(recursos.get(0).getIdRecurso(), idRol);	
		}
		Rol rol = rolService.findById(idRol);
		
		mav.addObject("recursos", recursos);
		mav.addObject("privilegiosAsignados", privilegiosAsignados);
		mav.addObject("privilegiosNoAsignados", privilegiosNoAsignados);
		mav.addObject("rol", rol);
		mav.addObject("resourcePrivileges", getRecursosPrivilegios(rol));
		
		return mav;
	}
	
	@PostMapping("/asignar-recurso-privilegio")
	public String asignarRrecursoPrivilegio(@RequestParam("idRol") Integer idRol, @RequestParam("idRecurso") Integer idRecurso, @RequestParam(name = "privilegios[]", required = false) List<Integer> privilegios) {
		
		Rol rol = rolService.findById(idRol);
		Recurso recurso = recursoService.findById(idRecurso);
		List<RolRecursoPrivilegio> rrps = rolRecursoPrivilegioService.findByRolAndRecurso(recurso.getIdRecurso(), rol.getIdRol());
		
		rolRecursoPrivilegioService.destroyAll(rrps);
		
		if(privilegios != null) {
									
			for(int idPrivilegio: privilegios) {
				Privilegio privilegio = privilegioService.findById(idPrivilegio);
				RolRecursoPrivilegio rrp = new RolRecursoPrivilegio();
				
				rrp.setPrivilegio(privilegio);
				rrp.setRecurso(recurso);
				rrp.setRol(rol);
				
				rolRecursoPrivilegioService.store(rrp);
			}
		}
		
		return "redirect:/rol/index";
	}
	
	@GetMapping("/rol/{idRol}/recurso/{idRecurso}")
	public ModelAndView loadPrivileges(@PathVariable("idRecurso") int idRecurso, @PathVariable("idRol") int idRol) {
		ModelAndView mav = new ModelAndView(LOAD_PRIVILEGES);
		List<Privilegio> noAsignados = privilegioService.privilegiosNoAsignadosByRecurso(idRecurso, idRol);
		List<Privilegio> asignados = privilegioService.privilegiosAsignadosByRecurso(idRecurso, idRol);
		
		mav.addObject("asignados", asignados);
		mav.addObject("noAsignados", noAsignados);
		
		return mav;
	}
	
	public List<RoleResource> getRecursosPrivilegios(Rol rol) {
		int idRecurso = 0;
		List<RoleResource> rolesRes = new ArrayList<RoleResource>();
		List<RolePrivilege> rolesPriv = new ArrayList<RolePrivilege>();
		
		for(RolRecursoPrivilegio rrp : rol.getRolesRecursosPrivilegios()) {
						
			if(idRecurso != rrp.getRecurso().getIdRecurso()) {
				rolesPriv = new ArrayList<RolePrivilege>();
				
			}
			
			RolePrivilege rolePriv = new RolePrivilege();
			rolePriv.setId(rrp.getPrivilegio().getIdPrivilegio());
			rolePriv.setName(rrp.getPrivilegio().getPrivilegio());
			
			rolesPriv.add(rolePriv);
			
			if(idRecurso != rrp.getRecurso().getIdRecurso()) {
				idRecurso = rrp.getRecurso().getIdRecurso();
				RoleResource roleRes = new RoleResource();
				roleRes.setId(rrp.getRecurso().getIdRecurso());
				roleRes.setName(rrp.getRecurso().getRecurso());
				
				roleRes.setPrivileges(rolesPriv);
				
				rolesRes.add(roleRes);				
			}
		}
		
		return rolesRes;
	}
	
}
