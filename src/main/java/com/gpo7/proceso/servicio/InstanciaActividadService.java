package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.InstanciaActividad;
import com.gpo7.proceso.entity.InstanciaProceso;

public interface InstanciaActividadService {

	public abstract InstanciaActividad store(InstanciaActividad instanciaAcitvidad);
	public abstract InstanciaActividad update(InstanciaActividad instanciaAcitvidad);
	public abstract InstanciaActividad findById(Integer idInstancia);
	public abstract InstanciaActividad findByElementoBpmnAndInstanciaProceso(ElementoBpmn eb, InstanciaProceso ip);
	public abstract InstanciaActividad getCurrActivity(Boolean finalizada, Integer instanciaProcId);
	public abstract InstanciaActividad getActivityByCargo(int cargoId, int instanciaProcesoId, boolean finalizada);
	public abstract List<InstanciaActividad> findByInstanciaProceso(InstanciaProceso ip);
}
