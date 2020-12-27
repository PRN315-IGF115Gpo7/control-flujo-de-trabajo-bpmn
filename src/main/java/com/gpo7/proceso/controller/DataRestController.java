package com.gpo7.proceso.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.ElementoBpmnFormulario;
import com.gpo7.proceso.entity.ElementoFormulario;
import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.TipoElementoBpmn;
import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.entity.Variable;
import com.gpo7.proceso.repository.UserJpaRepository;
import com.gpo7.proceso.servicio.CargoService;
import com.gpo7.proceso.servicio.ElementoBpmnFormularioService;
import com.gpo7.proceso.servicio.ElementoBpmnService;
import com.gpo7.proceso.servicio.ElementoFormularioService;
import com.gpo7.proceso.servicio.PrivilegioService;
import com.gpo7.proceso.servicio.ProcesoService;
import com.gpo7.proceso.servicio.TipoElementoService;
import com.gpo7.proceso.servicio.VariableService;

import objects.ElementoDiagrama;

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
	public String createOrReplaceElementoBpmnPost(
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
		return "Exito: " + elementoBpmn.getIdElementoBpmn();
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
	
	@GetMapping("/obtener-variables-elemento/{procesoId}/{elementoBpmnId}")
	public List<Variable> obtenerVariablesElemento(@PathVariable("procesoId") int procesoId,
			@PathVariable("elementoBpmnId") String elementoBpmnId){
		
		Proceso proceso = this.procesoService.findById(procesoId);
		ElementoBpmn elementoBpmn = this.elementoBpmnService.findByIdElementoDiagramaBpmnAndProceso(elementoBpmnId, proceso);
		
		List<ElementoBpmnFormulario> elementosBpmnFormulario = this.elementoBpmnFormularioService.findByElementoBpmn(elementoBpmn);
		List<Variable> variables = new ArrayList<Variable>();
				
		if(elementosBpmnFormulario != null) {
			for(ElementoBpmnFormulario elementoBpmnFormulario : elementosBpmnFormulario) {
				Variable variable = elementoBpmnFormulario.getElementoFormulario().getVariable();
				variable.setEsEscritura(elementoBpmnFormulario.isPermitirEscritura());
				variables.add(variable);
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
	
}
