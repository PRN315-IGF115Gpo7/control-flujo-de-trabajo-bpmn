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

import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.ElementoBpmnFormulario;
import com.gpo7.proceso.entity.ElementoFormulario;
import com.gpo7.proceso.entity.InstanciaActividad;
import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.Respuesta;
import com.gpo7.proceso.entity.TipoDato;
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
import com.gpo7.proceso.servicio.TipoDatoService;
import com.gpo7.proceso.servicio.UsuarioService;
import com.gpo7.proceso.servicio.VariableService;

@Controller
@RequestMapping("/proceso")
public class ProcesoController {

	// Views
	private static final String INDEX_VIEW = "proceso/index";
	private static final String CREATE_VIEW = "proceso/create";
	private static final String DIAGRAM_VIEW = "proceso/diagram";
	private static final String HISTORIAL_VIEW = "proceso/historial";
	private static final String ACTIVE_PROCESS_VIEW = "proceso/procesos-activos";
	private static final String REPLY_PROCESS_VIEW = "proceso/procesos-respondidos";
	private static final String REPLY_ACTIVITY_VIEW = "proceso/responder-actividad";
	private static final String RESULTADOS_VIEW = "proceso/resultados";
	private static final String RESPUESTAS_VIEW = "proceso/respuestas";
	private static final String FREE_DIAGRAM_VIEW = "proceso/free-diagram";

	// BPMN elements
	private static final String START_EVENT = "bpmn:StartEvent";
	private static final String END_EVENT = "bpmn:EndEvent";
	private static final String TASK = "bpmn:Task";
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
	@GetMapping({ "/index", "" })
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

		if (variables == null) {
			variables = new ArrayList<Variable>();
		}

		if (results.hasErrors() || variables.isEmpty()) {
			if (variables.isEmpty()) {
				results.reject("variable", "Debe asignar variables al proceso para continuar");
			}
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/proceso/create";
		}
		session.setAttribute("variables", new ArrayList<Variable>());

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioService.findByUsername(user.getUsername());

		//Variables almacenadas
		List<Variable> variablesStored = new ArrayList<Variable>();
			
		//proceso.setVariables(variablesStored);
		proceso.setUsuario(usuario);
		proceso.setEsAutomatizado(true);
		proceso.setProcesoActivo(true);
		procesoService.store(proceso);
		
		for (Variable variable : variables) {
			variable.setProceso(proceso);
			Variable variableStored = variableService.store(variable);
			variablesStored.add(variableStored);
			
			// Funcion para Crear Elemento_Formulario
			crearElementoFormulario(variableStored);
		}

		return "redirect:/proceso/diagram/" + proceso.getIdProceso();
	}

	public void crearElementoFormulario(Variable variable) {
		ElementoFormulario elementoFormulario = new ElementoFormulario();
		elementoFormulario.setLabel(variable.getVariableNombre());
		elementoFormulario.setVariable(variable);
		String tipoElementoFormulario = "";

		switch (variable.getTipoDato().getTipoDatoNombre()) {
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
	public @ResponseBody void agregarVariable(HttpServletRequest request, @PathVariable("nombre") String nombre,
			@PathVariable("tipo") int tipo) {
		Variable variable = new Variable();
		TipoDato tipoDato = tipoDatoService.findById(tipo);

		variable.setVariableNombre(nombre);
		variable.setTipoDato(tipoDato);

		HttpSession session = request.getSession(true);
		List<Variable> variablesSession = (ArrayList<Variable>) session.getAttribute("variables");
		Integer variableId = (Integer) session.getAttribute("variableId");

		if (variablesSession == null || variableId == null) {
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
		mav.addObject("cargo", usuario.getCargo());

		return mav;
	}

	@PreAuthorize("hasAuthority('PROCESO_INDEX')")
	@GetMapping("/{id}/procesos-respondidos")
	public ModelAndView replyProcess(@PathVariable int id) {
		ModelAndView mav = new ModelAndView(REPLY_PROCESS_VIEW);

		Proceso proceso = procesoService.findById(id);
		List<InstanciaProceso> instanciasProceso = instanciaProcesoService.findByProcesoAndFinalizado(proceso, true);

		mav.addObject("instanciaProcesos", instanciasProceso);

		return mav;
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("proceso") Proceso proceso, BindingResult results,
			RedirectAttributes redirAttrs) {
		Proceso procesoMod = procesoService.findById(proceso.getIdProceso());

		procesoMod.setProcesoNombre(proceso.getProcesoNombre());
		procesoMod.setProcesoDescripcion(proceso.getProcesoDescripcion());

		if (results.hasErrors()) {
			redirAttrs.addFlashAttribute("errors", results.getAllErrors());
			return "redirect:/proceso/index";
		} else {
			procesoService.update(procesoMod);
			redirAttrs.addFlashAttribute("success", "El proceso fue modificado con éxito");
		}
		return "redirect:/proceso/index";
	}

	@GetMapping("/{id}/responder-actividad")
	public ModelAndView responderActividad(@PathVariable("id") Integer procesoId,
			@RequestParam(name = "instanciaId", required = false) Integer instanciaId, RedirectAttributes redirAttrs) {

		ModelAndView mav = new ModelAndView(REPLY_ACTIVITY_VIEW);
		Proceso proceso = procesoService.findById(procesoId);

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioService.findByUsername(user.getUsername());
		InstanciaProceso instanciaProceso = instanciaProcesoService.findByProcesoAndUsuario(proceso, usuario);
		
		ElementoBpmn firstElement = elementoBpmnService.findByProcesoAndElement(proceso, START_EVENT);
		proceso.setCargo(firstElement.getCargo());
		procesoService.update(proceso);

		// Si aún no hay instancias del proceso creadas y un participante intenta
		// responder la actividad
		if (instanciaId == null && !isProcessOwner(proceso)) {
			redirAttrs.addFlashAttribute("error", "Aún no hay procesos para participar");
			return new ModelAndView("redirect:/proceso/procesos-activos");
		}

		// Si es primerpublica vez que ingresa al proceso y es dueño se crea la instancia del
		// proceso y actividades
		if (instanciaProceso == null && isProcessOwner(proceso)) {
			instanciaProceso = crearInstancia(proceso, usuario);
		}

		// Si es diferente de null es porque es un participante del proceso no el dueño
		// y se obtiene la instancia en la que va a participar
		if (instanciaId != null) {
			instanciaProceso = instanciaProcesoService.findById(instanciaId);
		}

		InstanciaActividad currActivity = instanciaActividadService.getCurrActivity(false,
				instanciaProceso.getInstanciaProcesoId());
		InstanciaActividad activityCargo = instanciaActividadService.getActivityByCargo(usuario.getCargo().getIdCargo(),
				instanciaProceso.getInstanciaProcesoId(), false);

		// Si ya se respondió la última actividad
		if (activityCargo == null) {
			redirAttrs.addFlashAttribute("error", "No hay más actividades disponibles para este cargo");
			return new ModelAndView("redirect:/proceso/procesos-activos");
		}

		if (currActivity.getInstanciaActividadId() != activityCargo.getInstanciaActividadId()) {
			redirAttrs.addFlashAttribute("error", "Esta actividad le pertenece a un usuario con cargo "
					+ currActivity.getElementoBpmn().getCargo().getNombreCargo());
			return new ModelAndView("redirect:/proceso/procesos-activos");
		}

		// Agregar la respuestas a aquellas variables que solo son de lectura
		ElementoBpmn elementoBpmn = currActivity.getElementoBpmn();
		for (ElementoBpmnFormulario ebf : elementoBpmn.getElementoBpmnFormularios()) {
			if (!ebf.isPermitirEscritura()) {

				ElementoBpmnFormulario ebfEscritura = elementoBpmnFormularioService
						.findByElementoFormularioAndPermitirEscritura(ebf.getElementoFormulario(), true).get(0);
				InstanciaActividad prevActivity = instanciaActividadService.findByElementoBpmnAndInstanciaProceso(
						ebfEscritura.getElementoBpmn(), currActivity.getInstanciaProceso());
				Respuesta respuesta = respuestaService.findByInstanciaActividadAndElementoBpmnFormulario(prevActivity,
						ebfEscritura);
				ebf.setRespuesta(respuesta.getRespuesta());
			}
		}

		mav.addObject("currActivity", currActivity);
		mav.addObject("currElement", currActivity.getElementoBpmn().getIdElementoDiagramaBpmn());
		mav.addObject("diagramXml", proceso.getProceoXml());
		mav.addObject("activityElements", elementoBpmn);

		return mav;
	}

	public InstanciaProceso crearInstancia(Proceso proceso, Usuario usuario) {
		InstanciaProceso instanciaProceso = new InstanciaProceso();
		instanciaProceso.setProceso(proceso);
		instanciaProceso.setUsuario(usuario);
		instanciaProceso.setFinalizado(false);

		instanciaProcesoService.store(instanciaProceso);

		for (ElementoBpmn eb : proceso.getElementosBpmn()) {

			// No se crean instancias de actividad para eventos de inicio fin y/o elementos
			// que no tengan variables asignadas
			if (eb.getElementoBpmnFormularios().isEmpty()) {
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

	public boolean isProcessOwner(Proceso proceso) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = usuarioService.findByUsername(user.getUsername());

		return usuario.getCargo().getIdCargo() == proceso.getCargo().getIdCargo();
	}

	@PostMapping("/persistir-respuestas")
	public String persistirRespuestas(@RequestParam Map<String, String> requestParams, RedirectAttributes redirAttrs) {
		int instanciaActId = Integer.parseInt(requestParams.get("instanciaActividadId"));

		InstanciaActividad currActivity = instanciaActividadService.findById(instanciaActId);

		if (hasEmptyFields(currActivity, requestParams)) {
			int procesoId = currActivity.getInstanciaProceso().getProceso().getIdProceso();
			int instanciaId = currActivity.getInstanciaProceso().getInstanciaProcesoId();

			if (currActivity.getElementoBpmn().getTipoElementoBpmn().getNombreTipoElementoBpmn()
					.equals(EXCLUSIVE_GATEWAY)) {
				redirAttrs.addFlashAttribute("error", "Debe seleccionar una actividad para continuar");
				return "redirect:/proceso/" + procesoId + "/responder-actividad?instanciaId=" + instanciaId;
			} else {
				redirAttrs.addFlashAttribute("error", "Se deben completar todos los campos para continuar");
				return "redirect:/proceso/" + procesoId + "/responder-actividad";
			}
		}

		// Si se envía una actividad de tipo compuerta
		if (requestParams.get("nextActivity") != null) {
			List<ElementoBpmn> ebs = currActivity.getElementoBpmn().getReference_next();
			InstanciaProceso ip = currActivity.getInstanciaProceso();
			int ebNextId = Integer.parseInt(requestParams.get("nextActivity"));

			ElementoBpmn ebNext = elementoBpmnService.findById(ebNextId);
			
			if(ebNext.getTipoElementoBpmn().getNombreTipoElementoBpmn().equals(END_EVENT)) {
				finalizarProceso(ip);
			}else {
				InstanciaActividad nextActivity = instanciaActividadService.findByElementoBpmnAndInstanciaProceso(ebNext, ip);
				for (ElementoBpmn eb : ebs) {
					InstanciaActividad ia = instanciaActividadService.findByElementoBpmnAndInstanciaProceso(eb, ip);

					if(ia != null) {
						if (ia.getInstanciaActividadId() != nextActivity.getInstanciaActividadId()) {
							ia.setFinalizada(true);
							instanciaActividadService.update(ia);
						}
					}
				}
			}

			// Si la actividad es de tipo tarea
		} else {
			for (ElementoBpmnFormulario ebf : currActivity.getElementoBpmn().getElementoBpmnFormularios()) {

				if (!ebf.isPermitirEscritura()) {
					continue;
				}

				String name = ebf.getElementoFormulario().getLabel();
				String value = requestParams.get(name);

				// Almacenando respuesta para un elemento de tipo checkbox
				if (ebf.getElementoFormulario().getElementoFormularioTipo().equals("checkbox")) {
					if (value == null) {
						value = "";
					} else {
						value = "checked";
					}
				}

				// Creando el objeto respuesta
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

		if (eb.getReference_next().get(0).getTipoElementoBpmn().getNombreTipoElementoBpmn().equals(END_EVENT)) {
			InstanciaProceso instanciaProceso = currActivity.getInstanciaProceso();
			instanciaProceso.setFinalizado(true);

			instanciaProcesoService.update(instanciaProceso);
		}

		return "redirect:/proceso/procesos-activos";
	}

	public boolean hasEmptyFields(InstanciaActividad currActivity, Map<String, String> requestParams) {

		if (currActivity.getElementoBpmn().getTipoElementoBpmn().getNombreTipoElementoBpmn()
				.equals(EXCLUSIVE_GATEWAY)) {
			if (requestParams.get("nextActivity") == null) {
				return true;
			}
		}

		for (ElementoBpmnFormulario ebf : currActivity.getElementoBpmn().getElementoBpmnFormularios()) {
			String name = ebf.getElementoFormulario().getLabel();

			if (!ebf.isPermitirEscritura()) {
				continue;
			}

			if (!ebf.getElementoFormulario().getElementoFormularioTipo().equals("checkbox")) {
				if (requestParams.get(name).isEmpty()) {
					return true;
				}
			}
		}

		return false;
	}
	
	public void finalizarProceso(InstanciaProceso ip) {
		for(InstanciaActividad ia : ip.getInstanciasActividad()) {
			ia.setFinalizada(true);
			instanciaActividadService.update(ia);
		}
	}
	
	@GetMapping({ "/instancia/{id}/resultados" })
	public ModelAndView verResultados(@PathVariable("id") Integer instanciaProcesoId) {
		ModelAndView mav = new ModelAndView(RESULTADOS_VIEW);

		InstanciaProceso instanciaProceso = this.instanciaProcesoService.findById(instanciaProcesoId);
		List<InstanciaActividad> instanciasActividades = this.instanciaActividadService.findByInstanciaProceso(instanciaProceso);
		
		mav.addObject("usuario", instanciaProceso.getUsuario());
		mav.addObject("proceso", instanciaProceso.getProceso());
		//mav.addObject("xml", instanciaProceso.getProceso().getProceoXml());
		mav.addObject("instanciasActividades", instanciasActividades);
		return mav;
	}
	
	@GetMapping({ "/actividad/instancia/{id}/respuestas" })
	public ModelAndView verRespuestas(@PathVariable("id") Integer instanciaActividadId) {
		ModelAndView mav = new ModelAndView(RESPUESTAS_VIEW);

		InstanciaActividad instanciaActividad = this.instanciaActividadService.findById(instanciaActividadId);
		List<Respuesta> respuestas = this.respuestaService.findByInstanciaActividad(instanciaActividad);
		mav.addObject("respuestas", respuestas);
		
		return mav;
	}
	
	@GetMapping("bpmn-diagram")
	public ModelAndView freeDiagram() {
		ModelAndView mav = new ModelAndView(FREE_DIAGRAM_VIEW);
		
		return mav;
	}

}