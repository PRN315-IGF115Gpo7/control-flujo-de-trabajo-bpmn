package com.gpo7.proceso.repository;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.Proceso;

@Repository("procesoJpaRepository")
public interface ProcesoJpaRepository extends JpaRepository<Proceso, Serializable> {

	@Query(value = "SELECT * FROM procesos p2 WHERE p2.proceso_id IN\n" + 
			"(SELECT DISTINCT p.proceso_id FROM procesos p NATURAL JOIN elementos_bpmn eb WHERE p.proceso_activo=?1 AND eb.id_cargo=?2)", nativeQuery = true)
	public abstract List<Proceso> procesosActivos(Boolean activo, Integer cargo_id);
}
