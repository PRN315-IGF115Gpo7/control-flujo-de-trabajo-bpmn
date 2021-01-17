package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.Proceso;

public interface ElementoBpmnService {
	
	public List<ElementoBpmn> getAll();
	public ElementoBpmn store(ElementoBpmn elementoBpmn);
	public void destroy(ElementoBpmn elementoBpmn);
	public ElementoBpmn findById(int idElementoBpmn);
	public ElementoBpmn update(ElementoBpmn elementoBpmn);
	public void createOrReplace(ElementoBpmn elementoBpmn);
	public ElementoBpmn findByIdElementoDiagramaBpmnAndProceso(String idElementoDiagramaBpmn, Proceso proceso);
	public abstract ElementoBpmn findByProcesoAndElement(Proceso proceso, String element);
	public abstract ElementoBpmn getFirstActivityElement(String elementType, int procesoId, int cargoId);
	public abstract List<ElementoBpmn> findByProcesoAndCargo(int idProc, int idCargo);
	public List<ElementoBpmn> findByProceso(Proceso proceso);
}
