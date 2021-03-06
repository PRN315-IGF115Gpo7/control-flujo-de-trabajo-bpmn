package com.gpo7.proceso.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.TipoElementoBpmn;

@Repository("tipoElementoBpmnJpaRepository")
public interface TipoElementoBpmnJpaRepository extends JpaRepository<TipoElementoBpmn, Serializable>{
	public TipoElementoBpmn findByNombreTipoElementoBpmn(String nombreTipoElementoBpmn);
}
