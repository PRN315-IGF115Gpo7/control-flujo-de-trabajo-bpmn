package com.gpo7.proceso.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.Privilegio;

@Repository("privilegioJpaRepository")
public interface PrivilegioJpaRepository extends JpaRepository<Privilegio, Serializable> {

	@Query(value = "(SELECT * FROM privilegios) EXCEPT (SELECT p.id_privilegio, p.privilegio FROM roles as r NATURAL JOIN roles_recursos_privilegios NATURAL JOIN privilegios as p WHERE id_recurso=?1 AND id_rol=?2)", nativeQuery = true)
	public abstract List<Privilegio> privilegiosNoAsignadosByRecurso(int idRecurso, int idRol);
	
	@Query(value = "SELECT p.id_privilegio, p.privilegio FROM roles as r NATURAL JOIN roles_recursos_privilegios NATURAL JOIN privilegios as p WHERE id_recurso=?1 and id_rol=?2", nativeQuery = true)
	public abstract List<Privilegio> privilegiosAsignadosByRecurso(int idRecurso, int idRol);
}
