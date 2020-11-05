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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cargos")
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cargo", unique = true, nullable = false)
	private int idCargo;
	
	@NotEmpty(message = "Debe ingresar el nombre del cargo")
	@Column(name = "nombre_cargo", nullable = false)
	private String nombreCargo;
	
	@NotEmpty(message = "Debe ingresar la descripción del cargo")
	@Size(min = 6, message = "La descripción debe tener al menos seis caracteres")
	@Column(name = "descripcion_cargo", nullable = false)
	private String descripcionCargo;

	@OneToMany(mappedBy = "cargo", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Usuario> usuarios = new ArrayList();

	public Cargo() {
	}

	public Cargo(int idCargo, String nombreCargo, String descripcionCargo) {
		super();
		this.idCargo = idCargo;
		this.nombreCargo = nombreCargo;
		this.descripcionCargo = descripcionCargo;
	}

	public int getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}

	public String getNombreCargo() {
		return nombreCargo;
	}

	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}

	public String getDescripcionCargo() {
		return descripcionCargo;
	}

	public void setDescripcionCargo(String descripcionCargo) {
		this.descripcionCargo = descripcionCargo;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
