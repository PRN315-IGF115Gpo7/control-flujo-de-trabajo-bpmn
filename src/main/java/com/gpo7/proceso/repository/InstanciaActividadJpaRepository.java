package com.gpo7.proceso.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.InstanciaActividad;
import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.Usuario;

@Repository("instanciaActividadJpaRepository")
public interface InstanciaActividadJpaRepository extends JpaRepository<InstanciaActividad, Serializable>{

	@Query(value = "SELECT * FROM instancias_actividades ia WHERE ia.finalizada = ?1 AND ia.instancia_proceso_id = ?2 ORDER BY ia.instancia_actividad_id ASC LIMIT 1", nativeQuery = true)
	public abstract InstanciaActividad getCurrActivity(Boolean finalizada, Integer instanciaProcId);
	public abstract InstanciaActividad findByElementoBpmnAndInstanciaProceso(ElementoBpmn eb, InstanciaProceso ip);
}
