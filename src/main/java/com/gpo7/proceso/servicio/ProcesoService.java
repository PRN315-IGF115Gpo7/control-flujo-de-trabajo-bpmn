package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Proceso;

public interface ProcesoService {
	
	public List<Proceso> getAll();
	public Proceso store(Proceso proceso);
	public Proceso update(Proceso proceso);
	public void destroy(Proceso proceso);
	public Proceso findById(int proceso_id);
}
