package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Usuario;

public interface UsuarioService {

	public abstract List<Usuario> getAdminUsers();
	public abstract Boolean isAdminUser(String username);
	public abstract Usuario findByUsername(String username);
}
