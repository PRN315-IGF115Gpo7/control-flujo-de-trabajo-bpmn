package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Permiso;

public interface PermisoService {
	public Permiso store(Permiso permiso);
	public List<Permiso> getAll();
	public void destroy(Permiso permiso);
}
