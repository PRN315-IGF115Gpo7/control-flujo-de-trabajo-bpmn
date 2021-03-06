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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "privilegios")
public class Privilegio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_privilegio")
	private Integer idPrivilegio;
	
	@Column(name = "privilegio")
	private String privilegio;
	
	@OneToMany(mappedBy = "privilegio", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<RolRecursoPrivilegio> rolesRecursosPrivilegios =  new ArrayList<>();
	
	public Privilegio() {}

	public Privilegio(Integer idPrivilegio, String privilegio) {
		this.idPrivilegio = idPrivilegio;
		this.privilegio = privilegio;
	}

	public Privilegio(Integer idPrivilegio, String privilegio, List<RolRecursoPrivilegio> rolesRecursosPrivilegios) {
		super();
		this.idPrivilegio = idPrivilegio;
		this.privilegio = privilegio;
		this.rolesRecursosPrivilegios = rolesRecursosPrivilegios;
	}

	public Integer getIdPrivilegio() {
		return idPrivilegio;
	}

	public void setIdPrivilegio(Integer idPrivilegio) {
		this.idPrivilegio = idPrivilegio;
	}

	public String getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(String privilegio) {
		this.privilegio = privilegio;
	}

	public List<RolRecursoPrivilegio> getRolesRecursosPrivilegios() {
		return rolesRecursosPrivilegios;
	}

	public void setRolesRecursosPrivilegios(List<RolRecursoPrivilegio> rolesRecursosPrivilegios) {
		this.rolesRecursosPrivilegios = rolesRecursosPrivilegios;
	}

}