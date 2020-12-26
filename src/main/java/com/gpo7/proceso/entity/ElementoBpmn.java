package com.gpo7.proceso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "elementos_bpmn")
public class ElementoBpmn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "elemento_bpmn_id", unique = true, nullable = false)
	private int idElementoBpmn;
	
	@Column(name = "elemento_bpmn_nombre", nullable = false)
	private String nombreElementoBpmn;
	
	@Column(name = "elemento_diagrama_bpmn_id", nullable = false)
	private String idElementoDiagramaBpmn;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tipo_elemento_bpmn_id")
    private TipoElementoBpmn tipoElementoBpmn;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="proceso_id")
    private Proceso proceso;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_cargo")
    private Cargo cargo;
	
	/**
	 * 
	 */
	public ElementoBpmn() {
		super();
	}

	/**
	 * @param idElementoBpmn
	 * @param nombreElementoBpmn
	 * @param idElementoDiagramaBpmn
	 * @param tipoElementoBpmn
	 * @param proceso
	 */
	public ElementoBpmn(int idElementoBpmn, String nombreElementoBpmn, String idElementoDiagramaBpmn,
			TipoElementoBpmn tipoElementoBpmn, Proceso proceso) {
		super();
		this.idElementoBpmn = idElementoBpmn;
		this.nombreElementoBpmn = nombreElementoBpmn;
		this.idElementoDiagramaBpmn = idElementoDiagramaBpmn;
		this.tipoElementoBpmn = tipoElementoBpmn;
		this.proceso = proceso;
	}
	
	

	/**
	 * @param nombreElementoBpmn
	 * @param idElementoDiagramaBpmn
	 * @param tipoElementoBpmn
	 * @param proceso
	 */
	public ElementoBpmn(String nombreElementoBpmn, String idElementoDiagramaBpmn, TipoElementoBpmn tipoElementoBpmn,
			Proceso proceso) {
		super();
		this.nombreElementoBpmn = nombreElementoBpmn;
		this.idElementoDiagramaBpmn = idElementoDiagramaBpmn;
		this.tipoElementoBpmn = tipoElementoBpmn;
		this.proceso = proceso;
	}

	public int getIdElementoBpmn() {
		return idElementoBpmn;
	}

	public void setIdElementoBpmn(int idElementoBpmn) {
		this.idElementoBpmn = idElementoBpmn;
	}

	public String getNombreElementoBpmn() {
		return nombreElementoBpmn;
	}

	public void setNombreElementoBpmn(String nombreElementoBpmn) {
		this.nombreElementoBpmn = nombreElementoBpmn;
	}

	public TipoElementoBpmn getTipoElementoBpmn() {
		return tipoElementoBpmn;
	}

	public void setTipoElementoBpmn(TipoElementoBpmn tipoElementoBpmn) {
		this.tipoElementoBpmn = tipoElementoBpmn;
	}



	public String getIdElementoDiagramaBpmn() {
		return idElementoDiagramaBpmn;
	}



	public void setIdElementoDiagramaBpmn(String idElementoDiagramaBpmn) {
		this.idElementoDiagramaBpmn = idElementoDiagramaBpmn;
	}



	public Proceso getProceso() {
		return proceso;
	}



	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "ElementoBpmn [idElementoBpmn=" + idElementoBpmn + ", nombreElementoBpmn=" + nombreElementoBpmn
				+ ", idElementoDiagramaBpmn=" + idElementoDiagramaBpmn + ", tipoElementoBpmn=" + tipoElementoBpmn
				+ ", proceso=" + proceso + "]";
	}

	
	
}
