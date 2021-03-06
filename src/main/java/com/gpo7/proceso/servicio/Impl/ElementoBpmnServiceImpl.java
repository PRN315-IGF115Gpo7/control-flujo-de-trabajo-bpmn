package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.repository.ElementoBpmnJpaRepository;
import com.gpo7.proceso.servicio.ElementoBpmnService;

@Service("elementoBpmnServiceImpl")
public class ElementoBpmnServiceImpl implements ElementoBpmnService{
	
	@Autowired
	@Qualifier("elementoBpmnJpaRepository")
	private ElementoBpmnJpaRepository elementoBpmnJpaRepository;

	@Override
	public List<ElementoBpmn> getAll() {
		return elementoBpmnJpaRepository.findAll();
	}

	@Override
	public ElementoBpmn store(ElementoBpmn elementoBpmn) {
		return elementoBpmnJpaRepository.save(elementoBpmn);
	}

	@Override
	public void destroy(ElementoBpmn elementoBpmn) {
		elementoBpmnJpaRepository.delete(elementoBpmn);
	}

	@Override
	public ElementoBpmn findById(int idElementoBpmn) {
		return elementoBpmnJpaRepository.findById(idElementoBpmn).get();
	}

	@Override
	public ElementoBpmn update(ElementoBpmn elementoBpmn) {
		return elementoBpmnJpaRepository.save(elementoBpmn);
	}

	@Override
	public void createOrReplace(ElementoBpmn elementoBpmn) {
		elementoBpmnJpaRepository.save(elementoBpmn);
	}

	@Override
	public ElementoBpmn findByIdElementoDiagramaBpmnAndProceso(String idElementoDiagramaBpmn, Proceso proceso) {
		return elementoBpmnJpaRepository.findByIdElementoDiagramaBpmnAndProceso(idElementoDiagramaBpmn, proceso);
	}

	@Override
	public ElementoBpmn findByProcesoAndElement(Proceso proceso, String element) {
		// TODO Auto-generated method stub
		return elementoBpmnJpaRepository.findByProcesoAndElement(proceso, element);
	}

	@Override
	public ElementoBpmn getFirstActivityElement(String elementType, int procesoId, int cargoId) {
		// TODO Auto-generated method stub
		return elementoBpmnJpaRepository.getFirstActivityElement(elementType, procesoId, cargoId);
	}

	@Override
	public List<ElementoBpmn> findByProcesoAndCargo(int idProc, int idCargo) {
		// TODO Auto-generated method stub
		return elementoBpmnJpaRepository.findByProcesoAndCargo(idProc, idCargo);
	}

	@Override
	public List<ElementoBpmn> findByProceso(Proceso proceso) {
		// TODO Auto-generated method stub
		return this.elementoBpmnJpaRepository.findByProceso(proceso);
	}
}
