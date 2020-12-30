package com.gpo7.proceso.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.ElementoBpmnFormulario;
import com.gpo7.proceso.entity.InstanciaActividad;
import com.gpo7.proceso.entity.Respuesta;

@Repository("respuestaRepository")
public interface RespuestaJpaRepository extends JpaRepository<Respuesta, Serializable>{

	public abstract Respuesta findByInstanciaActividadAndElementoBpmnFormulario(InstanciaActividad ia, ElementoBpmnFormulario ebf);
}
