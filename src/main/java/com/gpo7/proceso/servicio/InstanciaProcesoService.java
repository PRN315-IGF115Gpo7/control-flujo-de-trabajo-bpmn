package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.Usuario;

public interface InstanciaProcesoService {
	public List<InstanciaProceso> getAll();
	public abstract List<InstanciaProceso> findByUsuarioAndFinalizado(Usuario usuario, Boolean finalizado);
	public abstract List<InstanciaProceso> findByProcesoAndFinalizado(Proceso proceso, Boolean finalizado);
	public abstract InstanciaProceso findByProcesoAndUsuario(Proceso proceso, Usuario usuario);
	public abstract InstanciaProceso store(InstanciaProceso instanciaProceso);
	public abstract InstanciaProceso update(InstanciaProceso instanciaProceso);
	public abstract InstanciaProceso findById(int instanciaProcesoId);
}
