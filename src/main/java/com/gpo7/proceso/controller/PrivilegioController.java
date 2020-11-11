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

import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Rol;
import com.gpo7.proceso.servicio.PrivilegioService;
import com.gpo7.proceso.servicio.RolRecursoPrivilegioService;

@Controller
@RequestMapping("/privilegio")
public class PrivilegioController {

    @Autowired
    @Qualifier("privilegioServiceImpl")
    private PrivilegioService privilegioService;

    @Autowired
    @Qualifier("rolRecursoPrivilegioServiceImpl")
    private RolRecursoPrivilegioService rolRecursoPrivilegioService;

    private static final String INDEX_VIEW = "privilegio/index";

    @GetMapping("/index")
    public ModelAndView index(){
    	
        ModelAndView mav = new ModelAndView(INDEX_VIEW);
        mav.addObject("privilegios", privilegioService.getAll());
        mav.addObject("privilegio", new Privilegio());
        
        return mav;
    }

    @PostMapping("/store")
    public String store(@RequestParam("privilegio") String privilegio, RedirectAttributes redirAttrs){
    	
    	if(privilegio == null || privilegio == "") {
    		redirAttrs.addFlashAttribute("empty_error", "Debe ingresar el nombre dle privilegio");
    		return "redirect:/privilegio/index";
    	}
    	
    	Privilegio nuevoPrivilegio = new Privilegio();
    	nuevoPrivilegio.setPrivilegio(privilegio);
    	
    	privilegioService.store(nuevoPrivilegio);
    	return "redirect:/privilegio/index";
    }
    
    @PostMapping("/update")
    public String update(@RequestParam("idPrivilegio") Integer idPrivilegio, 
    		@RequestParam("privilegio") String privilegio,
    		RedirectAttributes redirAttrs){
    	
    	if(privilegio == null || privilegio == "") {
    		redirAttrs.addFlashAttribute("empty_error", "Debe ingresar el nombre dle privilegio");
    		return "redirect:/privilegio/index";
    	}
    	
    	Privilegio nuevoPrivilegio = privilegioService.findById(idPrivilegio);
    	
    	nuevoPrivilegio.setPrivilegio(privilegio);
    	
    	privilegioService.store(nuevoPrivilegio);
    	return "redirect:/privilegio/index";
    }
    
    @PostMapping("/destroy")
    public String destroy(@RequestParam("idPrivilegio") Integer idPrivilegio, RedirectAttributes redirAttrs){

        Privilegio privilegio = privilegioService.findById(idPrivilegio);

        if (privilegio.getRolesRecursosPrivilegios().size() > 0){
        	redirAttrs.addFlashAttribute("destroy_error", "Este privilegio no puede ser borrado porque ya fue asignado");
        	return "redirect:/privilegio/index";
        }
        
        privilegioService.destroy(privilegio);
        return "redirect:/privilegio/index";
    }
}