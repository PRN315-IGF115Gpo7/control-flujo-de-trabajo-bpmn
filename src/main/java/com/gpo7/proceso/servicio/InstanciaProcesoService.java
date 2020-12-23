package com.gpo7.proceso.servicio;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.Usuario;

public interface InstanciaProcesoService {
	public abstract List<InstanciaProceso> findByUsuarioAndFinalizado(Usuario usuario, Boolean finalizado);
	public abstract List<InstanciaProceso> findByProcesoAndFinalizado(Proceso proceso, Boolean finalizado);
	public List<InstanciaProceso> getAll();
}
