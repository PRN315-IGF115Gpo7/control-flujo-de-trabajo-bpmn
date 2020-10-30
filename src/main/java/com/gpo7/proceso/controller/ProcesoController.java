package com.gpo7.proceso.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gpo7.proceso.entity.TipoDato;
import com.gpo7.proceso.entity.Variable;
import com.gpo7.proceso.servicio.ProcesoService;
import com.gpo7.proceso.servicio.TipoDatoService;
import com.gpo7.proceso.servicio.VariableService;

@Controller
@RequestMapping("/proceso")
public class ProcesoController {

	private static final String CREATE_VIEW = "proceso/create";
	
	@Autowired
	@Qualifier("procesoServiceImpl")
	private ProcesoService procesoService;
	
	@Autowired
	@Qualifier("variableServiceImpl")
	private VariableService variableService;
	
	@Autowired
	@Qualifier("tipoDatoServiceImpl")
	private TipoDatoService tipoDatoService;
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView(CREATE_VIEW);
		
		mav.addObject("proceso", new ProcesoController());
		mav.addObject("tiposDato", tipoDatoService.getAll());
		
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
		
		if(variablesSession == null) {
			variablesSession = new ArrayList<Variable>();
		}
		
		variablesSession.add(variable);
		session.setAttribute("variables", variablesSession);
		
	}
	
	@GetMapping("/obtener-variables")
	public @ResponseBody List<Variable> obenerVariables(HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		List<Variable> variablesSession = (ArrayList<Variable>) session.getAttribute("variables");
	
		return variablesSession;
	}
	
	@GetMapping("/remover-variable/{id}")
	public void removeVariable(@PathVariable("id") int id, HttpServletRequest request) {
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
