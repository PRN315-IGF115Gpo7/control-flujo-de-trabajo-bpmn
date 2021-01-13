package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.ElementoBpmnFormulario;
import com.gpo7.proceso.entity.InstanciaActividad;
import com.gpo7.proceso.entity.Respuesta;

public interface RespuestaService {

	public abstract Respuesta store(Respuesta respuesta);
	public abstract Respuesta findByInstanciaActividadAndElementoBpmnFormulario(InstanciaActividad ia, ElementoBpmnFormulario ebf);
	public abstract List<Respuesta> findByInstanciaActividad(InstanciaActividad instanciaActividad);
}
