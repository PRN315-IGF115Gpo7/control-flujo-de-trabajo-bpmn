package com.gpo7.proceso.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "instancias_actividades")
public class InstanciaActividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "instancia_actividad_id")
	private Integer instanciaActividadId;
	
	private Boolean finalizada;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "elemento_bpmn_id")
	private ElementoBpmn elementoBpmn;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instancia_proceso_id")
	private InstanciaProceso instanciaProceso;
	
	public InstanciaActividad() {
	}

	public InstanciaActividad(Integer instanciaActividadId, Boolean finalizada, ElementoBpmn elementoBpmn,
			InstanciaProceso instanciaProceso) {
		super();
		this.instanciaActividadId = instanciaActividadId;
		this.finalizada = finalizada;
		this.elementoBpmn = elementoBpmn;
		this.instanciaProceso = instanciaProceso;
	}

	public Integer getInstanciaActividadId() {
		return instanciaActividadId;
	}

	public void setInstanciaActividadId(Integer instanciaActividadId) {
		this.instanciaActividadId = instanciaActividadId;
	}

	public Boolean getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}

	public ElementoBpmn getElementoBpmn() {
		return elementoBpmn;
	}

	public void setElementoBpmn(ElementoBpmn elementoBpmn) {
		this.elementoBpmn = elementoBpmn;
	}

	public InstanciaProceso getInstanciaProceso() {
		return instanciaProceso;
	}

	public void setInstanciaProceso(InstanciaProceso instanciaProceso) {
		this.instanciaProceso = instanciaProceso;
	}
	
}
