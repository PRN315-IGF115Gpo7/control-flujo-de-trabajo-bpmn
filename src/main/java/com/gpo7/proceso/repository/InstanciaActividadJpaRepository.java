package com.gpo7.proceso.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.InstanciaActividad;
import com.gpo7.proceso.entity.InstanciaProceso;

@Repository("instanciaActividadJpaRepository")
public interface InstanciaActividadJpaRepository extends JpaRepository<InstanciaActividad, Serializable>{

	@Query(value = "SELECT * FROM instancias_actividades ia WHERE ia.finalizada = ?1 AND ia.instancia_proceso_id = ?2 ORDER BY ia.instancia_proceso_id ASC LIMIT 1", nativeQuery = true)
	public abstract InstanciaActividad getCurrActivity(Boolean finalizada, Integer instanciaProcId);
	
	@Query(value = "SELECT ia.* FROM  instancias_actividades ia NATURAL JOIN elementos_bpmn eb WHERE eb.id_cargo = ?1 AND ia.instancia_proceso_id = ?2 AND ia.finalizada = ?3 ORDER BY ia.elemento_bpmn_id ASC LIMIT 1", nativeQuery = true)
	public abstract InstanciaActividad getActivityByCargo(int cargoId, int instanciaProcesoId, boolean finalizada);
	public abstract InstanciaActividad findByElementoBpmnAndInstanciaProceso(ElementoBpmn eb, InstanciaProceso ip);
	public abstract List<InstanciaActividad> findByInstanciaProceso(InstanciaProceso ip);
}
