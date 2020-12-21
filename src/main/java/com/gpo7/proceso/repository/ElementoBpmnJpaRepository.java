package com.gpo7.proceso.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.TipoElementoBpmn;

@Repository("elementoBpmnJpaRepository")
public interface ElementoBpmnJpaRepository extends JpaRepository<ElementoBpmn, Serializable>{
	public ElementoBpmn findByIdElementoDiagramaBpmnAndProceso(String idElementoDiagramaBpmn, Proceso proceso);
}
