package com.gpo7.proceso.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.gpo7.proceso.entity.Permiso;
import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.ElementoBpmnFormulario;
import com.gpo7.proceso.entity.ElementoFormulario;
import com.gpo7.proceso.entity.InstanciaActividad;
import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.Respuesta;
import com.gpo7.proceso.entity.Rol;
import com.gpo7.proceso.entity.TipoDato;
import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.entity.Variable;
import com.gpo7.proceso.servicio.CargoService;
import com.gpo7.proceso.servicio.ElementoBpmnFormularioService;
import com.gpo7.proceso.servicio.ElementoBpmnService;
import com.gpo7.proceso.servicio.ElementoFormularioService;
import com.gpo7.proceso.servicio.InstanciaActividadService;
import com.gpo7.proceso.servicio.InstanciaProcesoService;
import com.gpo7.proceso.servicio.ProcesoService;
import com.gpo7.proceso.servicio.RespuestaService;
import com.gpo7.proceso.servicio.InstanciaProcesoService;
import com.gpo7.proceso.servicio.TipoDatoService;
import com.gpo7.proceso.servicio.UsuarioService;
import com.gpo7.proceso.servicio.VariableService;

@Controller
@RequestMapping("/proceso")
public class ProcesoController {

	//Views
	private static final String INDEX_VIEW = "proceso/index";
	private static final String CREATE_VIEW = "proceso/create";
	private static final String DIAGRAM_VIEW = "proceso/diagram";
	private static final String HISTORIAL_VIEW = "proceso/historial";
	private static final String ACTIVE_PROCESS_VIEW = "proceso/procesos-activos";
	private static final String REPLY_PROCESS_VIEW = "proceso/procesos-respondidos";
	private static final String REPLY_ACTIVITY_VIEW = "proceso/responder-actividad";
	
	//BPMN elements
	private static final String START_EVENT = "bpmn:StartEvent";
	private static final String END_EVENT = "bpmn:EndEvent";
	private static final String EXCLUSIVE_GATEWAY = "bpmn:ExclusiveGateway";
	
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
	
	@Autowired 
	@Qualifier("instanciaProcesoServiceImpl")
	private InstanciaProcesoService instanciaProcesoService;
	
	@Autowired
	@Qualifier("cargoServiceImpl")
	private CargoService cargoService;
	
	@Autowired
	@Qualifier("elementoFormularioServiceImpl")
	private ElementoFormularioService elementoFormularioService;
	
	@Autowired
	@Qualifier("instanciaActividadServiceImpl")
	private InstanciaActividadService instanciaActividadService;
	
	@Autowired
	@Qualifier("elementoBpmnServiceImpl")
	private ElementoBpmnService elementoBpmnService;
	
	@Autowired
	@Qualifier("respuestaServiceImpl")
	private RespuestaService respuestaService;
	
	@Autowired
	@Qualifier("elementoBpmnFormularioServiceImpl")
	private ElementoBpmnFormularioService elementoBpmnFormularioService;
		
	@PreAuthorize("hasAuthority('PROCESO_INDEX')")
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView(CREATE_VIEW);
		
		mav.addObject("proceso", new Proceso());
		mav.addObject("tiposDato", tipoDatoService.getAll());
		
		return mav;
	}
	@PreAuthorize("hasAuthority('PROCESO_INDEX')")
	@GetMapping({"/index", ""})
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView(INDEX_VIEW);

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioService.findByUsername(user.getUsername());

	    mav.addObject("procesos", procesoService.getAll());
	    mav.addObject("proceso", new Proceso());
	    mav.addObject("usuarios", usuario);
	    return mav;
	}
	
	
	@GetMapping("/historial")
	public ModelAndView historial() {
		ModelAndView mav = new ModelAndView(HISTORIAL_VIEW);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioService.findByUsername(user.getUsername());
		
		mav.addObject("usuarios", usuario);
		mav.addObject("instancias", instanciaProcesoService.getAll());
		mav.addObject("instancia", new InstanciaProceso());
		
		return mav;
	}
	
	@PreAuthorize("hasAuthority('PROCESO_CREATE')")
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
				results.reject("variable", "Debe asignar variables al proceso para continuar");
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
			Variable variableStored = variableService.store(variable);
			
			//Funcion para Crear Elemento_Formulario
			crearElementoFormulario(variableStored);
		}

		return "redirect:/proceso/diagram/"+ proceso.getIdProceso();
	}
	
	public void crearElementoFormulario(Variable variable) {
		ElementoFormulario elementoFormulario = new ElementoFormulario();
		elementoFormulario.setLabel(variable.getVariableNombre());
		elementoFormulario.setVariable(variable);
		String tipoElementoFormulario = "";
		
		switch(variable.getTipoDato().getTipoDatoNombre()) {
			case "String":
				tipoElementoFormulario = "text";
				break;
			case "Date":
				tipoElementoFormulario = "date";
				break;	
			case "Boolean":
				tipoElementoFormulario = "checkbox";
				break;
			default:
				tipoElementoFormulario = "number";
		}
		elementoFormulario.setElementoFormularioTipo(tipoElementoFormulario);
		this.elementoFormularioService.store(elementoFormulario);
	}
	
	@GetMapping("/diagram/{proceso_id}")
	public ModelAndView diagram(@PathVariable("proceso_id") int procesoId) {
		ModelAndView mav = new ModelAndView(DIAGRAM_VIEW);
		mav.addObject("proceso", procesoService.findById(procesoId));
		mav.addObject("cargos", cargoService.getAll());
		return mav;
	}
	
	@PreAuthorize("hasAuthority('PROCESO_CREATE')")
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
	
	@PreAuthorize("hasAuthority('PROCESO_CREATE')")
	@GetMapping("/obtener-variables")
	public @ResponseBody List<Variable> obenerVariables(HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		List<Variable> variablesSession = (ArrayList<Variable>) session.getAttribute("variables");
	
		return variablesSession;
	}
	
	@PreAuthorize("hasAuthority('PROCESO_CREATE')")
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
	
	@GetMapping("/procesos-activos")
	public ModelAndView activeProcess() {
		ModelAndView mav = new ModelAndView(ACTIVE_PROCESS_VIEW);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioService.findByUsername(user.getUsername());
		
		List<Proceso> procesos = procesoService.procesosActivos(true, usuario.getCargo().getIdCargo());
		
	    mav.addObject("procesos", procesos);
	    
	    return mav;
	}
	
	@PreAuthorize("hasAuthority('PROCESO_INDEX')")
	@GetMapping("/{id}/procesos-respondidos")
	public ModelAndView replyProcess(@PathVariable int id) {
		ModelAndView mav = new ModelAndView(REPLY_PROCESS_VIEW);
		
		
		Proceso proceso = procesoService.findById(id);
		List<InstanciaProceso> instanciasProceso = instanciaProcesoService.findByProcesoAndFinalizado(proceso,true);
		
		mav.addObject("instanciaProcesos", instanciasProceso);
		
		return mav;		
	}
	
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("proceso") Proceso proceso, BindingResult results, RedirectAttributes redirAttrs) {		
		Proceso procesoMod = procesoService.findById(proceso.getIdProceso());
		
		procesoMod.setProcesoNombre(proceso.getProcesoNombre());
		procesoMod.setProcesoDescripcion(proceso.getProcesoDescripcion());	
		
		if(results.hasErrors()) {
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/proceso/index";
		}	
		else {
			procesoService.update(procesoMod);
			redirAttrs.addFlashAttribute("success", "El proceso fue modificado con éxito");
		}
		return "redirect:/proceso/index";
	}
	
	@GetMapping("/{id}/responder-actividad")
	public ModelAndView responderProceso(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView(REPLY_ACTIVITY_VIEW);
		Proceso proceso = procesoService.findById(id);
		//ElementoBpmn currActivity = null;
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioService.findByUsername(user.getUsername());
		InstanciaProceso instanciaProceso = instanciaProcesoService.findByProcesoAndUsuario(proceso, usuario);
		
		//Si es primera vez que ingresa al proceso se crea la instancia del proceso y actividades
		if(instanciaProceso == null) {
			instanciaProceso = crearInstancia(proceso, usuario);
		}
		
		InstanciaActividad currActivity = instanciaActividadService.getCurrActivity(false, instanciaProceso.getInstanciaProcesoId());
		
		if(currActivity == null) {
			return new ModelAndView("redirect:/proceso/procesos-activos");
		}
		
		ElementoBpmn elementoBpmn = currActivity.getElementoBpmn();
		for(ElementoBpmnFormulario ebf : elementoBpmn.getElementoBpmnFormularios()) {
			if(!ebf.isPermitirEscritura()) {
				
				ElementoBpmnFormulario ebfEscritura = elementoBpmnFormularioService.findByElementoFormularioAndPermitirEscritura(ebf.getElementoFormulario(), true).get(0);
				InstanciaActividad prevActivity = instanciaActividadService.findByElementoBpmnAndInstanciaProceso(ebfEscritura.getElementoBpmn(), currActivity.getInstanciaProceso());
				Respuesta respuesta = respuestaService.findByInstanciaActividadAndElementoBpmnFormulario(prevActivity, ebfEscritura);
				ebf.setRespuesta(respuesta.getRespuesta());
			}
		}
		
		mav.addObject("currActivity", currActivity);
		mav.addObject("activityElements", elementoBpmn);
		
		return mav;
	}
	
	public InstanciaProceso crearInstancia(Proceso proceso, Usuario usuario) {
		InstanciaProceso instanciaProceso = new InstanciaProceso();
		instanciaProceso.setProceso(proceso);
		instanciaProceso.setUsuario(usuario);
		instanciaProceso.setFinalizado(false);
		
		instanciaProcesoService.store(instanciaProceso);
		
		for(ElementoBpmn eb : proceso.getElementosBpmn()) {
			
			if(eb.getTipoElementoBpmn().getNombreTipoElementoBpmn().equals("bpmn:StartEvent") ||
					eb.getTipoElementoBpmn().getNombreTipoElementoBpmn().equals("bpmn:EndEvent")) {
				continue;
			}
			
			InstanciaActividad ia = new InstanciaActividad();
			
			ia.setElementoBpmn(eb);
			ia.setInstanciaProceso(instanciaProceso);
			ia.setFinalizada(false);
			
			instanciaActividadService.store(ia);
		}
		
		return instanciaProceso;
	}

	@PostMapping("/persistir-respuestas")
	public String persistirRespuestas(@RequestParam Map<String, String> requestParams) {
		int instanciaActId =  Integer.parseInt(requestParams.get("instanciaActividadId"));
				
		InstanciaActividad currActivity = instanciaActividadService.findById(instanciaActId);
		
		if(requestParams.get("nextActivity") != null) {
			//int elementBpmnId = Integer.parseInt(requestParams.get("nextActivity"));
			//ElementoBpmn elementoBpmn = elementoBpmnService.findById(elementBpmnId);
			List<ElementoBpmn> ebs = currActivity.getElementoBpmn().getReference_next();
			
			for(ElementoBpmn eb : ebs) {
				InstanciaProceso ip = currActivity.getInstanciaProceso();
				InstanciaActividad ia = instanciaActividadService.findByElementoBpmnAndInstanciaProceso(eb, ip);
				
				if(ia.getInstanciaActividadId() != instanciaActId) {
					ia.setFinalizada(true);
					instanciaActividadService.update(ia);
				}
			}
		}else {
			for(ElementoBpmnFormulario ebf : currActivity.getElementoBpmn().getElementoBpmnFormularios()) {
				String name = ebf.getElementoFormulario().getLabel();
				String value = requestParams.get(name);
				
				Respuesta respuesta = new Respuesta();
				respuesta.setRespuesta(value);
				respuesta.setElementoBpmnFormulario(ebf);
				respuesta.setInstanciaActividad(currActivity);
				
				respuestaService.store(respuesta);
			}
		}
		
		currActivity.setFinalizada(true);
		instanciaActividadService.update(currActivity);
		
		ElementoBpmn eb = currActivity.getElementoBpmn();
		
		if(eb.getReference_next().get(0).getTipoElementoBpmn().getNombreTipoElementoBpmn().equals(END_EVENT)) {
			InstanciaProceso instanciaProceso = currActivity.getInstanciaProceso();
			instanciaProceso.setFinalizado(true);
			
			instanciaProcesoService.update(instanciaProceso);
		}
		
		return "redirect:/proceso/procesos-activos";
	}
}
