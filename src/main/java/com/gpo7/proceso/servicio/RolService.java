package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Rol;



public interface RolService {
	
	public Rol store(Rol rol);
	public List<Rol> getAll();
	public Rol update(Rol rol);
	public void destroy(Rol rol);
	public Rol findById(int idRol);
	
	public abstract List<Rol> getAvailableRoles(int idUsuario);
	public abstract List<Rol> getUserRoles(int idUsuario);
	
}
