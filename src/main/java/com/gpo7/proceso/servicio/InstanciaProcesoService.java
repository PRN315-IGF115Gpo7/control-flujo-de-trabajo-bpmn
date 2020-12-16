package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.entity.Usuario;

public interface InstanciaProcesoService {
	public abstract List<InstanciaProceso> findByUsuarioAndFinalizado(Usuario usuario, Boolean finalizado);
	public List<InstanciaProceso> getAll();
}
