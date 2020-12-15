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
@Table(name = "instancias_procesos")
public class InstanciaProceso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "instancia_proceso_id")
	private Integer instanciaProcesoId;
	
	@Column(name = "finalizado")
	private Boolean finalizado;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "proceso_id")
	private Proceso proceso;
		
	public InstanciaProceso() {}

	public InstanciaProceso(Integer instanciaProcesoId, Boolean finalizado, Usuario usuario,
			Proceso proceso) {
		super();
		this.instanciaProcesoId = instanciaProcesoId;
		this.finalizado = finalizado;
		this.usuario = usuario;
		this.proceso = proceso;
	}

	public Integer getInstanciaProcesoId() {
		return instanciaProcesoId;
	}

	public void setInstanciaProcesoId(Integer instanciaProcesoId) {
		this.instanciaProcesoId = instanciaProcesoId;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
}