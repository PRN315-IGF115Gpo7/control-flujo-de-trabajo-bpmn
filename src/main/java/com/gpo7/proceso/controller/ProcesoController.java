package com.gpo7.proceso.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.TipoDato;
import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.entity.Variable;
import com.gpo7.proceso.servicio.ProcesoService;
import com.gpo7.proceso.servicio.TipoDatoService;
import com.gpo7.proceso.servicio.UsuarioService;
import com.gpo7.proceso.servicio.VariableService;

@Controller
@RequestMapping("/proceso")
public class ProcesoController {

	private static final String CREATE_VIEW = "proceso/create";
	private static final String DIAGRAM_VIEW = "proceso/diagram";
	
	@Autowired
	@Qualifier("procesoServiceImpl")
	private ProcesoService procesoService;
	
	@Autowired
	@Qualifier("variableServiceImpl")
	private VariableService variableService;
	
	@Autowired
	@Qualifier("tipoDatoServiceImpl")
	private TipoDatoService tipoDatoService;
	
	@Autowired 
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioService;
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView(CREATE_VIEW);
		
		mav.addObject("proceso", new Proceso());
		mav.addObject("tiposDato", tipoDatoService.getAll());
		
		return mav;
	}
	
	@PostMapping("/store")
	public String store(@Valid @ModelAttribute("proceso") Proceso proceso, BindingResult results, 
			HttpServletRequest request, RedirectAttributes redirAttrs) {
		HttpSession session = request.getSession(true);
		
		List<Variable> variables = (ArrayList<Variable>) session.getAttribute("variables");
				
		if(variables == null) {
			variables = new ArrayList<Variable>();
		}
		
		if(results.hasErrors() || variables.isEmpty()) {
			if(variables.isEmpty()) {
				results.reject("variable", "Debe asginar variables al proceso para continuar");
			}
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/proceso/create";
		}
		session.setAttribute("variables", new ArrayList<Variable>());
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioService.findByUsername(user.getUsername());
		
		proceso.setVariables(variables);
		proceso.setUsuario(usuario);
		proceso.setEsAutomatizado(true);
		proceso.setProcesoActivo(true);
		procesoService.store(proceso);
		
		for (Variable variable : variables) {
			variable.setProceso(proceso);
			variableService.store(variable);
		}
		
		return "redirect:/proceso/diagram";
	}
	
	@GetMapping("/diagram")
	public ModelAndView diagram() {
		ModelAndView mav = new ModelAndView(DIAGRAM_VIEW);
		
		return mav;
	}
	
	@GetMapping("/agregar-variable/{nombre}/{tipo}")
	public @ResponseBody void agregarVariable(HttpServletRequest request,
			@PathVariable("nombre") String nombre, @PathVariable("tipo") int tipo) {
		Variable variable = new Variable();
		TipoDato tipoDato = tipoDatoService.findById(tipo);
		
		variable.setVariableNombre(nombre);
		variable.setTipoDato(tipoDato);
		
		HttpSession session = request.getSession(true);
		List<Variable> variablesSession = (ArrayList<Variable>) session.getAttribute("variables");
		Integer variableId = (Integer) session.getAttribute("variableId");
		
		if(variablesSession == null || variableId == null) {
			variablesSession = new ArrayList<Variable>();
			variableId = 1;
		}
		
		variable.setIdVariable(variableId++);
		variablesSession.add(variable);
		session.setAttribute("variables", variablesSession);
		session.setAttribute("variableId", variableId);
		
	}
	
	@GetMapping("/obtener-variables")
	public @ResponseBody List<Variable> obenerVariables(HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		List<Variable> variablesSession = (ArrayList<Variable>) session.getAttribute("variables");
	
		return variablesSession;
	}
	
	@GetMapping("/remover-variable/{id}")
	public @ResponseBody void removeVariable(@PathVariable("id") int id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Variable> variablesSession = (ArrayList<Variable>) session.getAttribute("variables");

		for (Variable variable : variablesSession) {
			if (id == variable.getIdVariable()) {
				variablesSession.remove(variable);

				break;
			}
		}
	}
}
