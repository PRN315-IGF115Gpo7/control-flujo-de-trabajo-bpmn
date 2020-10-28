package com.gpo7.proceso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cargos")
public class Cargo {
	@Id
	@GeneratedValue
	@Column(name = "id_cargo", unique = true, nullable = false)
	private int idCargo;
	@Column(name = "nombre_cargo", nullable = false)
	private String nombreCargo;
	@Column(name = "descripcion_cargo", nullable = false)
	private String descripcionCargo;
	
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

}
