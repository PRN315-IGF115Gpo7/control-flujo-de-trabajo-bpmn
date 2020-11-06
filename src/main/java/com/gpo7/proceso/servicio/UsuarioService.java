package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Rol;
import com.gpo7.proceso.entity.Usuario;

public interface UsuarioService {

	public abstract List<Usuario> getAdminUsers();
	public abstract Boolean isAdminUser(String username);
	public Usuario update(Usuario usuario);
	public abstract Usuario findByUsername(String username);
	public List<Usuario> getAll();
	public abstract Usuario findById(int idUsuario);
	public Usuario store(Usuario usuario);
}
