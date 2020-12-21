package com.gpo7.proceso.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tipos_elemento_bpmn")
public class TipoElementoBpmn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipo_elemento_bpmn_id", unique = true, nullable = false)
	private int idTipoElementoBpmn;
	
	@Column(name = "tipo_elemento_bpmn_nombre", nullable = false)
	private String nombreTipoElementoBpmn;
	
	@OneToMany(mappedBy = "tipoElementoBpmn", fetch = FetchType.EAGER)
	@JsonIgnore
    private List<ElementoBpmn> elementosBbmn =  new ArrayList<>();
	
	/**
	 * 
	 */
	public TipoElementoBpmn() {
		super();
	}

	/**
	 * @param idTipoElementoBpmn
	 * @param nombreTipoElementoBpmn
	 * @param elementosBbmn
	 */
	public TipoElementoBpmn(int idTipoElementoBpmn, String nombreTipoElementoBpmn, List<ElementoBpmn> elementosBbmn) {
		super();
		this.idTipoElementoBpmn = idTipoElementoBpmn;
		this.nombreTipoElementoBpmn = nombreTipoElementoBpmn;
		this.elementosBbmn = elementosBbmn;
	}

	/**
	 * @param nombreTipoElementoBpmn
	 * @param elementosBbmn
	 */
	public TipoElementoBpmn(String nombreTipoElementoBpmn, List<ElementoBpmn> elementosBbmn) {
		super();
		this.nombreTipoElementoBpmn = nombreTipoElementoBpmn;
		this.elementosBbmn = elementosBbmn;
	}

	public int getIdTipoElementoBpmn() {
		return idTipoElementoBpmn;
	}

	public void setIdTipoElementoBpmn(int idTipoElementoBpmn) {
		this.idTipoElementoBpmn = idTipoElementoBpmn;
	}

	public String getNombreTipoElementoBpmn() {
		return nombreTipoElementoBpmn;
	}

	public void setNombreTipoElementoBpmn(String nombreTipoElementoBpmn) {
		this.nombreTipoElementoBpmn = nombreTipoElementoBpmn;
	}

	public List<ElementoBpmn> getElementosBbmn() {
		return elementosBbmn;
	}

	public void setElementosBbmn(List<ElementoBpmn> elementosBbmn) {
		this.elementosBbmn = elementosBbmn;
	}

	@Override
	public String toString() {
		return "TipoElementoBpmn [idTipoElementoBpmn=" + idTipoElementoBpmn + ", nombreTipoElementoBpmn="
				+ nombreTipoElementoBpmn + ", elementosBbmn=" + elementosBbmn + "]";
	}
}
