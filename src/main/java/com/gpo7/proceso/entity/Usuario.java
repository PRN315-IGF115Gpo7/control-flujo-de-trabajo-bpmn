package com.gpo7.proceso.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

	@Id
	@GeneratedValue
	@Column(name = "id_usuario", unique = true, nullable = false)
	private int idUsuario;
	
	@NotEmpty(message="Debe ingresar nombre de usuario")
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@NotEmpty(message = "Debe ingresar el nombre completo del usuario")
	@Column(name = "nombre_completo")
	private String nombreCompleto;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "account_experired", nullable = false)
	private boolean accountExperired;

	@Column(name = "account_locked", nullable = false)
	private boolean accountLocked;

	@Column(name = "password_expired", nullable = false)
	private boolean passwordExpired;

	@Column(name = "intentos", nullable = false)
	private int intentos = 0;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cargo_id")
    private Cargo cargo;
	
	@Email(message = "El formato email ingresado es incorrecto")
	@NotEmpty(message = "Debe ingresar el email del usuario")
	@Column(name = "email", unique = true, nullable = true)
	private String email;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "usuarios_roles", 
		joinColumns = {
			@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false) }, 
		inverseJoinColumns = {
					@JoinColumn(name = "id_rol", referencedColumnName = "id_rol", nullable = false) })
	private List<Rol> roles = new ArrayList<>();
	/*
	 * @OneToMany(fetch = FetchType.EAGER, mappedBy = "user") private Set<UserRole>
	 * userRole = new HashSet<UserRole>();
	 */

	public Usuario() {
		this.password = "flujos";//crea clave por defecto admin
	}

	public Usuario(String username, String password, boolean enabled, boolean accountExperired, boolean accountLocked, boolean passwordExpired, int intentos, List<Rol> roles) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountExperired = accountExperired;
		this.accountLocked = accountLocked;
		this.passwordExpired = passwordExpired;
		this.intentos = intentos;
		this.roles = roles;
	}

	public Usuario(int idUser, String username, String password, boolean enabled, boolean accountExperired,
				   boolean accountLocked, boolean passwordExpired, int intentos) {
		super();
		this.idUsuario = idUser;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountExperired = accountExperired;
		this.accountLocked = accountLocked;
		this.passwordExpired = passwordExpired;
		this.intentos = intentos;
	}

	public Usuario(int idUsuario, String username, String password, boolean enabled, boolean accountExperired,
			boolean accountLocked, boolean passwordExpired, int intentos, List<Rol> roles) {
		super();
		this.idUsuario = idUsuario;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountExperired = accountExperired;
		this.accountLocked = accountLocked;
		this.passwordExpired = passwordExpired;
		this.intentos = intentos;
		this.roles = roles;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountExperired() {
		return accountExperired;
	}

	public void setAccountExperired(boolean accountExperired) {
		this.accountExperired = accountExperired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isPasswordExpired() {
		return passwordExpired;
	}

	public void setPasswordExpired(boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

}
