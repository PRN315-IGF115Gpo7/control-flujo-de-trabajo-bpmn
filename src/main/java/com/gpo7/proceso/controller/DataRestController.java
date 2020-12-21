package com.gpo7.proceso.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.TipoElementoBpmn;
import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.repository.UserJpaRepository;
import com.gpo7.proceso.servicio.ElementoBpmnService;
import com.gpo7.proceso.servicio.PrivilegioService;
import com.gpo7.proceso.servicio.ProcesoService;
import com.gpo7.proceso.servicio.TipoElementoService;

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
	
}
