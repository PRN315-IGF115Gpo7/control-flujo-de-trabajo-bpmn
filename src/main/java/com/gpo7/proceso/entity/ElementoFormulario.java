package com.gpo7.proceso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "elementos_formulario")
public class ElementoFormulario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "elemento_formulario_id")
	private int elementoFormularioId;
	
	@Column(name = "label")
	private String label;
	
	@Column(name = "elemento_formulario_tipo")
	private String elementoFormularioTipo;
	
	@OneToOne
	@JoinColumn(name = "variable_id")
	private Variable variable;

	public int getElementoFormularioId() {
		return elementoFormularioId;
	}

	public void setElementoFormularioId(int elementoFormularioId) {
		this.elementoFormularioId = elementoFormularioId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getElementoFormularioTipo() {
		return elementoFormularioTipo;
	}

	public void setElementoFormularioTipo(String elementoFormularioTipo) {
		this.elementoFormularioTipo = elementoFormularioTipo;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public ElementoFormulario(int elementoFormularioId, String label, String elementoFormularioTipo,
			Variable variable) {
		super();
		this.elementoFormularioId = elementoFormularioId;
		this.label = label;
		this.elementoFormularioTipo = elementoFormularioTipo;
		this.variable = variable;
	}
	
	public ElementoFormulario() {}
}
