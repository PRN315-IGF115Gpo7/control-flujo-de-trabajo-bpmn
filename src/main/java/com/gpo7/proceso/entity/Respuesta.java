package com.gpo7.proceso.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "respuestas")
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer respuestaId;
	
	private String respuesta;
	
	@ManyToOne
	@JoinColumn(name = "instancia_actividad_id")
	private InstanciaActividad instanciaActividad;
	
	@ManyToOne
	@JoinColumn(name = "elemento_bpmn_formulario_id")
	private ElementoBpmnFormulario elementoBpmnFormulario;

	public Respuesta() {
	}
	
	public Respuesta(Integer respuestaId, String respuesta, InstanciaActividad instanciaActividad,
			ElementoBpmnFormulario elementoBpmnFormulario) {
		super();
		this.respuestaId = respuestaId;
		this.respuesta = respuesta;
		this.instanciaActividad = instanciaActividad;
		this.elementoBpmnFormulario = elementoBpmnFormulario;
	}

	public Integer getRespuestaId() {
		return respuestaId;
	}

	public void setRespuestaId(Integer respuestaId) {
		this.respuestaId = respuestaId;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public InstanciaActividad getInstanciaActividad() {
		return instanciaActividad;
	}

	public void setInstanciaActividad(InstanciaActividad instanciaActividad) {
		this.instanciaActividad = instanciaActividad;
	}

	public ElementoBpmnFormulario getElementoBpmnFormulario() {
		return elementoBpmnFormulario;
	}

	public void setElementoBpmnFormulario(ElementoBpmnFormulario elementoBpmnFormulario) {
		this.elementoBpmnFormulario = elementoBpmnFormulario;
	}
	
}
