package com.gpo7.proceso.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "proceso_id")
	private Proceso proceso;
	
	@OneToOne(mappedBy = "variable", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private ElementoFormulario elementoFormulario = new ElementoFormulario();

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
