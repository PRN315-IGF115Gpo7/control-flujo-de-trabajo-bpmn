package com.gpo7.proceso.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.ElementoBpmnFormulario;
import com.gpo7.proceso.entity.ElementoFormulario;
import com.gpo7.proceso.entity.InstanciaActividad;
import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.TipoDato;
import com.gpo7.proceso.entity.TipoElementoBpmn;
import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.entity.Variable;
import com.gpo7.proceso.repository.UserJpaRepository;
import com.gpo7.proceso.servicio.CargoService;
import com.gpo7.proceso.servicio.ElementoBpmnFormularioService;
import com.gpo7.proceso.servicio.ElementoBpmnService;
import com.gpo7.proceso.servicio.ElementoFormularioService;
import com.gpo7.proceso.servicio.InstanciaActividadService;
import com.gpo7.proceso.servicio.InstanciaProcesoService;
import com.gpo7.proceso.servicio.PrivilegioService;
import com.gpo7.proceso.servicio.ProcesoService;
import com.gpo7.proceso.servicio.TipoDatoService;
import com.gpo7.proceso.servicio.TipoElementoService;
import com.gpo7.proceso.servicio.UsuarioService;
import com.gpo7.proceso.servicio.VariableService;

import objects.VariableStructDiagram;

@RestController
@RequestMapping("/api")
public class DataRestController {

	@Autowired
	@Qualifier("privilegioServiceImpl")
	private PrivilegioService privilegioService;
	
	@Autowired
    @Qualifier("userJpaRepository")
    private UserJpaRepository userJpaRepository;
	
	@Autowired
    @Qualifier("procesoServiceImpl")
    private ProcesoService procesoService;
	
	@Autowired
    @Qualifier("elementoBpmnServiceImpl")
    private ElementoBpmnService elementoBpmnService;
	
	@Autowired
    @Qualifier("tipoElementoServiceImpl")
    private TipoElementoService tipoElementoService;
	
	@Autowired
	@Qualifier("elementoFormularioServiceImpl")
	private ElementoFormularioService elementoFormularioService;
	
	@Autowired
	@Qualifier("elementoBpmnFormularioServiceImpl")
	private ElementoBpmnFormularioService elementoBpmnFormularioService;
	
	@Autowired
	@Qualifier("variableServiceImpl")
	private VariableService variableService;
	
	@Autowired
	@Qualifier("cargoServiceImpl")
	private CargoService cargoService;
	
	@Autowired
	@Qualifier("tipoDatoServiceImpl")
	private TipoDatoService tipoDatoService;
	
	@Autowired
	@Qualifier("instanciaProcesoServiceImpl")
	private InstanciaProcesoService instanciaProcesoService;
	
	@Autowired
	@Qualifier("instanciaActividadServiceImpl")
	private InstanciaActividadService instanciaActividadService;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioService;
	
	// BPMN elements
	private static final String START_EVENT = "bpmn:StartEvent";
	
	@GetMapping("/rol/{idRol}/recurso/{idRecurso}/no-asignados")
	public List<Privilegio> privilegiosNoAsignadosByRecurso(@PathVariable("idRecurso") int idRecurso, @PathVariable("idRol") int idRol){
		return privilegioService.privilegiosNoAsignadosByRecurso(idRecurso, idRol);
	}
	
	@GetMapping("/rol/{idRol}/recurso/{idRecurso}/asignados")
	public List<Privilegio> privilegiosAsignadosByRecurso(@PathVariable("idRecurso") int idRecurso, @PathVariable("idRol") int idRol){
		return privilegioService.privilegiosAsignadosByRecurso(idRecurso, idRol);
	}
	
	@GetMapping("/user/{username}")
	public Usuario getUsername(@PathVariable("username") String username) {
		return userJpaRepository.findByUsername(username);
	}
	
	@PostMapping("/elemento-bpmn/create")
	public ElementoBpmn createOrReplaceElementoBpmnPost(
			@RequestParam("id_diagrama_elemento_bpmn") String idElementoDiagramaBpmn,
			@RequestParam("nombre_elemento_bpmn") String nombreElementoBpmn,
			@RequestParam("tipo_elemento_bpmn") String tipoElementoBpmn,
			@RequestParam("id_proceso") int idProceso) {
		//System.out.println(idElementoDiagramaBpmn + " " + nombreElementoBpmn + " " + tipoElementoBpmn + " " + idProceso);
		Proceso proceso = procesoService.findById(idProceso);
		TipoElementoBpmn tipoElemento =  tipoElementoService.findByNombre(tipoElementoBpmn);
		ElementoBpmn elementoBpmn = elementoBpmnService.findByIdElementoDiagramaBpmnAndProceso(idElementoDiagramaBpmn, proceso);
		if(elementoBpmn == null) {
			elementoBpmn = new ElementoBpmn(nombreElementoBpmn, idElementoDiagramaBpmn, tipoElemento, proceso);
		}else {
			elementoBpmn.setNombreElementoBpmn(nombreElementoBpmn);
		}
		elementoBpmnService.createOrReplace(elementoBpmn);
		return elementoBpmn;
	}
	
	@PostMapping("/proceso/update-xml")
	public String updateProcesoPost(
			@RequestParam("xml") String xml,
			@RequestParam("id_proceso") int idProceso
			){
		Proceso proceso = procesoService.findById(idProceso);
		proceso.setProceoXml(xml);
		procesoService.update(proceso);
		return "Exito";
	}
	
	@PostMapping("/elemento-bpmn/update-participante")
	public String updateElementoBpmnPost(
			@RequestParam("id_diagrama_elemento_bpmn") String idElementoDiagramaBpmn,
			@RequestParam("id_proceso") int idProceso,
			@RequestParam("id_cargo") int idCargo
			){
		Proceso proceso = procesoService.findById(idProceso);
		Cargo cargo = cargoService.findById(idCargo);
		ElementoBpmn elementoBpmn = elementoBpmnService.findByIdElementoDiagramaBpmnAndProceso(idElementoDiagramaBpmn, proceso);
		elementoBpmn.setCargo(cargo);
		elementoBpmnService.createOrReplace(elementoBpmn);
		return "Exito";
	}
	
	@PostMapping("/elemento-bpmn/update-elemento-next")
	public String updateElementoBpmnNextPost(
			@RequestParam("id_diagrama_elemento_bpmn") String idElementoDiagramaBpmn,
			@RequestParam("id_proceso") int idProceso,
			@RequestParam("elemento_next") String elementoNext
			){
		System.out.println(elementoNext);
		Proceso proceso = procesoService.findById(idProceso);
		ElementoBpmn elementoBpmn = elementoBpmnService.findByIdElementoDiagramaBpmnAndProceso(idElementoDiagramaBpmn, proceso);
		ElementoBpmn elementoBpmnNext = elementoBpmnService.findByIdElementoDiagramaBpmnAndProceso(elementoNext, proceso);
		elementoBpmn.addElementoNext(elementoBpmnNext);
		elementoBpmnService.createOrReplace(elementoBpmn);
		return "Exito";
	}
	
	@PostMapping("/elemento-bpmn-formulario/create")
	public String createElementoBpmnForm(
			@RequestParam("procesoId") int procesoId, 
			@RequestParam("elementoBpmnId") String elementoBpmnId,
			@RequestParam("variableId") int  variableId,
			@RequestParam("esEscritura") boolean esEscritura) {
		
		Proceso proceso = this.procesoService.findById(procesoId);
		ElementoBpmn elementoBpmn = this.elementoBpmnService.findByIdElementoDiagramaBpmnAndProceso(elementoBpmnId, proceso);
		
		Variable variable = this.variableService.findById(variableId);
		ElementoFormulario elementoFormulario = this.elementoFormularioService.findByVariable(variable);
		
		ElementoBpmnFormulario elementoBpmnFormulario = new ElementoBpmnFormulario(elementoBpmn, elementoFormulario, esEscritura);
		
		this.elementoBpmnFormularioService.store(elementoBpmnFormulario);
		
		return "Exito";
	}
	
	@PostMapping("/finalizar-proceso")
	public RedirectView finalizarProceso(
			@RequestParam("procesoId") int procesoId) {
		Proceso proceso = this.procesoService.findById(procesoId);
		ElementoBpmn firstElement = elementoBpmnService.findByProcesoAndElement(proceso, START_EVENT);
		proceso.setCargo(firstElement.getCargo());
		proceso.setProcesoActivo(true);
		procesoService.update(proceso);
		System.out.println("Hola");
		return new RedirectView("/proceso/index");
	}
	
	
	@GetMapping("/obtener-variables-elemento/{procesoId}/{elementoBpmnId}")
	public List<VariableStructDiagram> obtenerVariablesElemento(@PathVariable("procesoId") int procesoId,
			@PathVariable("elementoBpmnId") String elementoBpmnId){
		
		Proceso proceso = this.procesoService.findById(procesoId);
		ElementoBpmn elementoBpmn = this.elementoBpmnService.findByIdElementoDiagramaBpmnAndProceso(elementoBpmnId, proceso);
		List<VariableStructDiagram> variables = new ArrayList<VariableStructDiagram>();
		List<Variable> varsProceso = this.variableService.findByProceso(proceso);
		
		List<ElementoBpmnFormulario> elementosBpmnFormulario = this.elementoBpmnFormularioService.findByElementoBpmn(elementoBpmn);
				
		if(elementosBpmnFormulario != null) {
			for(ElementoBpmnFormulario elementoBpmnFormulario : elementosBpmnFormulario) {
				Variable variable = elementoBpmnFormulario.getElementoFormulario().getVariable();
				//Es de escritura o no (arreglar)
				variables.add(new VariableStructDiagram(variable, elementoBpmnFormulario.isPermitirEscritura(), true));
			}
		}
		
		//Si es Compuerta, entonces se debe de averiguar cuales variables ya se han asignado previamente
		//para escritura
		if(elementoBpmn.getTipoElementoBpmn().getNombreTipoElementoBpmn().equals("bpmn:ExclusiveGateway")) {
			
			List<Variable> varsWritten = new ArrayList<Variable>(); 
			List<ElementoBpmn> elementosBpmnProceso = this.elementoBpmnService.findByProceso(proceso);
			if(elementosBpmnProceso.size() > 0) {
				for(ElementoBpmn elBpmn : elementosBpmnProceso) {
					if(elBpmn.getTipoElementoBpmn().getNombreTipoElementoBpmn().equals("bpmn:Task")) {
						List<ElementoBpmnFormulario> ebfs = 
								this.elementoBpmnFormularioService.findByElementoBpmnAndPermitirEscritura(elBpmn, true);
						for(ElementoBpmnFormulario ebf : ebfs) {
							varsWritten.add(ebf.getElementoFormulario().getVariable());
						}
					}
				}
			}
			
			//Ahora vamos a recorrer todas las variables y ver si estas no han sido aun puestas para escritura
			//De cumplir esta condicion, entonces la agregamos al array de *variables*, que son las que no se mostraran 
			//en el combobox para poder asignarlas en modo de lectura
			if(varsWritten.size() > 0) {
				for(Variable varProceso : varsProceso) {
					boolean written = false;
					for(Variable varWritten : varsWritten) {
						if(varProceso.getIdVariable() == varWritten.getIdVariable())
							written = true;
					}
					//Si la variable de proceso no se ha escrito, entonces la agregamos a *variables*
					if(! written) {
						variables.add(new VariableStructDiagram(varProceso, false, false));
					}
				}
			}else {
				//Si no se ha asignado ninguna de escritura, entonces no se debe mostrar ninguna variable
				//en el combobox, por eso las mandamos todas
				for(Variable varProceso: varsProceso) {
					variables.add(new VariableStructDiagram(varProceso, false, false));
				}
				return variables;
			}
		}
				
		return variables;
	}
	
	@GetMapping("/eliminar-variable-elemento/{procesoId}/{elementoBpmnId}/{variableId}")
	public String eliminarVariableElementoBpmn(
			@PathVariable("procesoId") int procesoId,
			@PathVariable("elementoBpmnId") String elementoBpmnId,
			@PathVariable("variableId") int variableId) {
		
		Proceso proceso = this.procesoService.findById(procesoId);
		ElementoBpmn elementoBpmn = this.elementoBpmnService.findByIdElementoDiagramaBpmnAndProceso(elementoBpmnId, proceso);
		
		Variable variable = this.variableService.findById(variableId);
		ElementoFormulario elementoFormulario= this.elementoFormularioService.findByVariable(variable);
		
		ElementoBpmnFormulario elementoBpmnFormulario = this.elementoBpmnFormularioService.findByElementoBpmnAndElementoFormulario(elementoBpmn, elementoFormulario);
		
		this.elementoBpmnFormularioService.destroy(elementoBpmnFormulario);
		
		return "Exito";
	}
	
	//Se obtienen las instancias del proceso para mostrarselas al participante del proceso al momento de responder uno
	@GetMapping("/proceso/{id}/instancias")
	public List<InstanciaProceso> getInstanciasByProceso(@PathVariable("id") Integer procesoId){
		Proceso proceso = procesoService.findById(procesoId);
		List<InstanciaProceso> instanciasProceso = proceso.getInstanciasProceso();
		
		for (InstanciaProceso ip : instanciasProceso) {
			InstanciaActividad currActivity = instanciaActividadService.getCurrActivity(false, ip.getInstanciaProcesoId());
			
			if (currActivity == null) {
				ip.setNombreActividad("Finalizado");
			} else {
				ip.setNombreActividad(currActivity.getElementoBpmn().getNombreElementoBpmn());
			}
			
			ip.setProceso(null);
		}
		
		return instanciasProceso;
	}
	
	@GetMapping("/proceso/{id}")
	public String getProceso(@PathVariable("id") int id) {
		Proceso proceso = procesoService.findById(id);
		
		return proceso.getProceoXml();
	}
	
	@GetMapping("/proceso/{idProc}/elementos")
	public List<ElementoBpmn> getElementosByProcesoAndCargo(@PathVariable("idProc") int idProc){
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.findByUsername(loggedInUser.getName());
		
		List<ElementoBpmn> elementosBpmn = elementoBpmnService.findByProcesoAndCargo(idProc, usuario.getCargo().getIdCargo());
		
		for(ElementoBpmn eb : elementosBpmn) {
			eb.setCargo(null);
			eb.setProceso(null);
			eb.setReference_next(null);
			eb.setReference_previous(null);
			eb.setElementoBpmnFormularios(null);
			eb.setTipoElementoBpmn(null);
		}
		
		return elementosBpmn;
	}
	
	@PostMapping("/agregar-variable")
	public Variable agregarVariable(
			@RequestParam("procesoId") int procesoId,
			@RequestParam("nombre") String nombre,
			@RequestParam("tipoDatoId") int tipoDatoId
			){
		
		Proceso proceso = procesoService.findById(procesoId);
		TipoDato tipoDato = tipoDatoService.findById(tipoDatoId);
		
		Variable var = new Variable();
		var.setVariableNombre(nombre);
		var.setTipoDato(tipoDato);
		var.setProceso(proceso);
		crearElementoFormulario(variableService.store(var));
		
		return var;
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
}
