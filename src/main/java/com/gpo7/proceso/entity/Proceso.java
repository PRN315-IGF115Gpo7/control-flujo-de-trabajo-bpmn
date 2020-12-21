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
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "procesos")
public class Proceso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proceso_id")
	private Integer idProceso;
	
	@NotEmpty(message = "Debe ingresar el nombre del proceso")
	@Column(name = "proceso_nombre")
	private String procesoNombre;
	
	@NotEmpty(message = "Debe ingresar una descripción para el proceso")
	@Column(name = "proceso_descripcion")
	private String procesoDescripcion;
	
	@Column(name = "proceso_activo")
	private Boolean procesoActivo;
	
	@Column(name = "proceso_xml", columnDefinition="TEXT")
	private String proceoXml;
	
	@Column(name = "es_automatizado")
	private Boolean esAutomatizado;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	
	@OneToMany(mappedBy = "proceso", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"proceso"})
    private List<Variable> variables =  new ArrayList();
	
	@OneToMany(mappedBy = "proceso", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<InstanciaProceso> instanciasProceso = new ArrayList();

	/**
	 * 
	 */
	public Proceso() {
		super();
	}

	public Proceso(Integer idProceso, String procesoNombre, String procesoDescripcion, Boolean procesoActivo,
			String proceoXml, Boolean esAutomatizado, Usuario usuario) {
		super();
		this.idProceso = idProceso;
		this.procesoNombre = procesoNombre;
		this.procesoDescripcion = procesoDescripcion;
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

	public String getProcesoDescripcion() {
		return procesoDescripcion;
	}

	public void setProcesoDescripcion(String procesoDescripcion) {
		this.procesoDescripcion = procesoDescripcion;
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

	public List<InstanciaProceso> getInstanciasProceso() {
		return instanciasProceso;
	}

	public void setInstanciasProceso(List<InstanciaProceso> instanciasProceso) {
		this.instanciasProceso = instanciasProceso;
	}
	
	public int contadorRespuestas(List<InstanciaProceso> instanciasProceso, Boolean finalizado)
	{
	    int count=0;
	    for (InstanciaProceso respuestas: instanciasProceso) {
	        if(String.valueOf(respuestas.getFinalizado()).toLowerCase().equals(String.valueOf(finalizado).toLowerCase()))
	            count+=1;
	    }
	    return count;
	}
}
