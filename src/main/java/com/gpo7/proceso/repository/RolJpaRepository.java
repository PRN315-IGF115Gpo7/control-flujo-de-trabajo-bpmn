package com.gpo7.proceso.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Rol;

@Repository("rolJpaRepository")
public interface RolJpaRepository extends JpaRepository<Rol, Serializable>{

	@Query(value = "SELECT * FROM roles WHERE id_rol NOT IN (SELECT id_rol FROM usuarios_roles WHERE id_usuario=?1)", nativeQuery = true)
	public abstract List<Rol> findAvailableRoles(int idUsuario);
	
	@Query(value = "SELECT * FROM roles WHERE id_rol IN (SELECT id_rol FROM usuarios_roles WHERE id_usuario=?1)", nativeQuery = true)
	public abstract List<Rol> findUserRoles(int idusuario);
}
