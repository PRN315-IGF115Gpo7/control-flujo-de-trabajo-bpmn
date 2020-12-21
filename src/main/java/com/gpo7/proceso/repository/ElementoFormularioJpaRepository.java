package com.gpo7.proceso.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.ElementoFormulario;
import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.Variable;

@Repository("elementoFormularioJpaRepository")
public interface ElementoFormularioJpaRepository extends JpaRepository<ElementoFormulario, Serializable>{

	public ElementoFormulario findByVariable(Variable variable);
}
