package com.gpo7.proceso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_datos")
public class TipoDato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipo_dato_id")
	private Integer idTipoDato;
	
	@Column(name = "tipo_dato_nombre")
	private String tipoDatoNombre;

	public TipoDato() {
	}

	public TipoDato(Integer idTipoDato, String tipoDatoNombre) {
		super();
		this.idTipoDato = idTipoDato;
		this.tipoDatoNombre = tipoDatoNombre;
	}

	public Integer getIdTipoDato() {
		return idTipoDato;
	}

	public void setIdTipoDato(Integer idTipoDato) {
		this.idTipoDato = idTipoDato;
	}

	public String getTipoDatoNombre() {
		return tipoDatoNombre;
	}

	public void setTipoDatoNombre(String tipoDatoNombre) {
		this.tipoDatoNombre = tipoDatoNombre;
	}
}
