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
@Table(name = "variables")
public class Variable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "variable_id")
	private Integer idVariable;
	
	@Column(name = "variable_nombre")
	private String variableNombre;
	
	@ManyToOne
	@JoinColumn(name = "tipo_dato_id")
	private TipoDato tipoDato;
	
	@ManyToOne
	@JoinColumn(name = "proceso_id")
	private Proceso proceso;

	public Variable() {
	}

	public Variable(Integer idVariable, String variableNombre, TipoDato tipoDato, Proceso proceso) {
		super();
		this.idVariable = idVariable;
		this.variableNombre = variableNombre;
		this.tipoDato = tipoDato;
		this.proceso = proceso;
	}

	public Integer getIdVariable() {
		return idVariable;
	}

	public void setIdVariable(Integer idVariable) {
		this.idVariable = idVariable;
	}

	public String getVariableNombre() {
		return variableNombre;
	}

	public void setVariableNombre(String variableNombre) {
		this.variableNombre = variableNombre;
	}

	public TipoDato getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}	
	
}
