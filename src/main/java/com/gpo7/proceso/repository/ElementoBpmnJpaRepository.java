package com.gpo7.proceso.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.Proceso;

@Repository("elementoBpmnJpaRepository")
public interface ElementoBpmnJpaRepository extends JpaRepository<ElementoBpmn, Serializable>{
	public ElementoBpmn findByIdElementoDiagramaBpmnAndProceso(String idElementoDiagramaBpmn, Proceso proceso);
	
	@Query(value = "SELECT * FROM elementos_bpmn eb WHERE eb.proceso_id = ?1 AND eb.id_cargo = ?2", nativeQuery = true)
	public abstract List<ElementoBpmn> findByProcesoAndCargo(int idProc, int idCargo);
	
	@Query(value = "SELECT eb.* FROM elementos_bpmn eb NATURAL JOIN tipos_elemento_bpmn teb WHERE eb.proceso_id = ?1 AND teb.tipo_elemento_bpmn_nombre = ?2", nativeQuery = true)
	public abstract ElementoBpmn findByProcesoAndElement(Proceso proceso, String element);
	
	@Query(value = "SELECT eb.* FROM elementos_bpmn eb NATURAL JOIN tipos_elemento_bpmn teb WHERE teb.tipo_elemento_bpmn_nombre = ?1 AND eb.proceso_id = ?2 AND eb.id_cargo = ?3 ORDER BY eb.elemento_bpmn_id ASC LIMIT 1", nativeQuery = true)
	public abstract ElementoBpmn getFirstActivityElement(String elementType, int procesoId, int cargoId);
}
