package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Rol;



public interface RolService {
	
	public Rol store(Rol rol);
	public List<Rol> getAll();
	
}
