package com.gpo7.proceso.servicio.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.ElementoBpmnFormulario;
import com.gpo7.proceso.repository.ElementoBpmnFormularioJpaRepository;
import com.gpo7.proceso.servicio.ElementoBpmnFormularioService;

@Service("elementoBpmnFormularioServiceImpl")
public class ElementoBpmnFormularioServiceImpl implements ElementoBpmnFormularioService{

	@Autowired
	@Qualifier("elementoBpmnFormularioJpaRepository")
	private ElementoBpmnFormularioJpaRepository elementoBpmnFormularioRepository; 
	@Override
	public ElementoBpmnFormulario store(ElementoBpmnFormulario elementoBpmnFormulario) {
		return this.elementoBpmnFormularioRepository.save(elementoBpmnFormulario);
	}

	@Override
	public ElementoBpmnFormulario update(ElementoBpmnFormulario elementoBpmnFormulario) {
		return this.elementoBpmnFormularioRepository.save(elementoBpmnFormulario);
	}

	@Override
	public void destroy(ElementoBpmnFormulario elementoBpmnFormulario) {

		this.elementoBpmnFormularioRepository.delete(elementoBpmnFormulario);
	}

	@Override
	public List<ElementoBpmnFormulario> index() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementoBpmnFormulario> findByElementoBpmn(ElementoBpmn elementoBpmn) {
		return this.elementoBpmnFormularioRepository.findByElementoBpmn(elementoBpmn);
	}

}
