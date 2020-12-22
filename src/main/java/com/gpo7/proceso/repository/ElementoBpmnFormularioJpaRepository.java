package com.gpo7.proceso.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.ElementoBpmnFormulario;
import com.gpo7.proceso.entity.ElementoFormulario;

@Repository("elementoBpmnFormularioJpaRepository")
public interface ElementoBpmnFormularioJpaRepository extends JpaRepository<ElementoBpmnFormulario, Serializable>{

	public List<ElementoBpmnFormulario> findByElementoBpmn(ElementoBpmn elementoBpmn);
	public ElementoBpmnFormulario findByElementoBpmnAndElementoFormulario(ElementoBpmn elementoBpmn, ElementoFormulario elementoFormulario);
}
