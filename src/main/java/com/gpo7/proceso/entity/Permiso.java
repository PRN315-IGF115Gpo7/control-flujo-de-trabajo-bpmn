package com.gpo7.proceso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "permisos")
public class Permiso {
	
	@Id
	@GeneratedValue
	@Column(name = "id_permiso", unique = true)
	private int idPermiso;
	
	@NotEmpty(message = "Debe ingresar un asunto para el permiso")
	@Column(name = "nombre_permiso")
	private String nombrePermiso;
	
	@NotEmpty(message = "Debe escribir la descripcion del permiso")
	@Size(min=10, message = "La descripcion debe tener mínimo 10 caracteres")
	@Column(name = "descripcion_permiso")
	private String descripcionPermiso;
	
	public Permiso(){
		
	}
	
	public Permiso(int idPermiso, String nombrePermiso, String descripcionPermiso) {
		super();
		this.idPermiso = idPermiso;
		this.nombrePermiso = nombrePermiso;
		this.descripcionPermiso = descripcionPermiso;
	}

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public String getNombrePermiso() {
		return nombrePermiso;
	}

	public void setNombrePermiso(String nombrePermiso) {
		this.nombrePermiso = nombrePermiso;
	}

	public String getDescripcionPermiso() {
		return descripcionPermiso;
	}

	public void setDescripcionPermiso(String descripcionPermiso) {
		this.descripcionPermiso = descripcionPermiso;
	}
	
}
