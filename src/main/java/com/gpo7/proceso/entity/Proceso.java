package com.gpo7.proceso.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "procesos")
public class Proceso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proceso_id")
	Integer idProceso;
	
	@Column(name = "proceso_nombre")
	String procesoNombre;
	
	@Column(name = "proceso_descripcion")
	String procesoDescripción;
	
	@Column(name = "proceso_activo")
	Boolean procesoActivo;
	
	@Column(name = "proceso_xml")
	String proceoXml;
	
	@Column(name = "es_automatizado")
	Boolean esAutomatizado;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "proceso", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"proceso"})
    private List<Variable> variables =  new ArrayList();

	public Proceso() {
	}

	public Proceso(Integer idProceso, String procesoNombre, String procesoDescripción, Boolean procesoActivo,
			String proceoXml, Boolean esAutomatizado, Usuario usuario) {
		super();
		this.idProceso = idProceso;
		this.procesoNombre = procesoNombre;
		this.procesoDescripción = procesoDescripción;
		this.procesoActivo = procesoActivo;
		this.proceoXml = proceoXml;
		this.esAutomatizado = esAutomatizado;
		this.usuario = usuario;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public String getProcesoNombre() {
		return procesoNombre;
	}

	public void setProcesoNombre(String procesoNombre) {
		this.procesoNombre = procesoNombre;
	}

	public String getProcesoDescripción() {
		return procesoDescripción;
	}

	public void setProcesoDescripción(String procesoDescripción) {
		this.procesoDescripción = procesoDescripción;
	}

	public Boolean getProcesoActivo() {
		return procesoActivo;
	}

	public void setProcesoActivo(Boolean procesoActivo) {
		this.procesoActivo = procesoActivo;
	}

	public String getProceoXml() {
		return proceoXml;
	}

	public void setProceoXml(String proceoXml) {
		this.proceoXml = proceoXml;
	}

	public Boolean getEsAutomatizado() {
		return esAutomatizado;
	}

	public void setEsAutomatizado(Boolean esAutomatizado) {
		this.esAutomatizado = esAutomatizado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

}
