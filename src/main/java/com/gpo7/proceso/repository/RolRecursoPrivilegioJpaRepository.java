package com.gpo7.proceso.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Recurso;
import com.gpo7.proceso.entity.Rol;
import com.gpo7.proceso.entity.RolRecursoPrivilegio;

@Repository("rolRecursoPrivilegioJpaRepository")
public interface RolRecursoPrivilegioJpaRepository extends JpaRepository<RolRecursoPrivilegio, Serializable> {

	@Query(value = "SELECT * FROM roles_recursos_privilegios WHERE id_recurso=?1 AND id_rol=?2", nativeQuery = true)
	public abstract List<RolRecursoPrivilegio> findByRolAndRecurso(int idRecurso, int idRol);
	
}