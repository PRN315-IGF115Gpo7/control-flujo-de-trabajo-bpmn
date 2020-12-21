package com.gpo7.proceso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "elementos_bpmn_formulario")
public class ElementoBpmnFormulario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "elementos_bpmn_formulario_id")
	private int elementosBpmnFormularioId;
	
	@ManyToOne
	@JoinColumn(name = "elemento_bpmn_id")
	private ElementoBpmn elementoBpmn;
	
	@ManyToOne
	@JoinColumn(name = "elemento_formulario_id")
	private ElementoFormulario elementoFormulario;
	
	@Column(name = "permitir_escritura")
	private boolean permitirEscritura;

	public int getElementosBpmnFormularioId() {
		return elementosBpmnFormularioId;
	}

	public void setElementosBpmnFormularioId(int elementosBpmnFormularioId) {
		this.elementosBpmnFormularioId = elementosBpmnFormularioId;
	}

	public ElementoBpmn getElementoBpmn() {
		return elementoBpmn;
	}

	public void setElementoBpmn(ElementoBpmn elementoBpmn) {
		this.elementoBpmn = elementoBpmn;
	}

	public ElementoFormulario getElementoFormulario() {
		return elementoFormulario;
	}

	public void setElementoFormulario(ElementoFormulario elementoFormulario) {
		this.elementoFormulario = elementoFormulario;
	}

	public boolean isPermitirEscritura() {
		return permitirEscritura;
	}

	public void setPermitirEscritura(boolean permitirEscritura) {
		this.permitirEscritura = permitirEscritura;
	}

	public ElementoBpmnFormulario(int elementosBpmnFormularioId, ElementoBpmn elementoBpmn,
			ElementoFormulario elementoFormulario, boolean permitirEscritura) {
		super();
		this.elementosBpmnFormularioId = elementosBpmnFormularioId;
		this.elementoBpmn = elementoBpmn;
		this.elementoFormulario = elementoFormulario;
		this.permitirEscritura = permitirEscritura;
	}
	
	public ElementoBpmnFormulario(ElementoBpmn elementoBpmn,
			ElementoFormulario elementoFormulario, boolean permitirEscritura) {
		super();
		this.elementoBpmn = elementoBpmn;
		this.elementoFormulario = elementoFormulario;
		this.permitirEscritura = permitirEscritura;
	}
	
	public ElementoBpmnFormulario() {}
	
}
