package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.Proceso;

public interface ProcesoService {
	
	public List<Proceso> getAll();
	public Proceso store(Proceso proceso);
	public Proceso update(Proceso proceso);
	public void destroy(Proceso proceso);
	public void deleteById(int procesoId);
	public Proceso findById(int proceso_id);
	public abstract List<Proceso> procesosActivos(Boolean activo, Integer cargo_id);
	public abstract int cantidadRespuestas(int procesoId);
}
