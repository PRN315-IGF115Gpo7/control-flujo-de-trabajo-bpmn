package com.gpo7.proceso.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.Usuario;

@Repository("userJpaRepository")
public interface UserJpaRepository extends JpaRepository<Usuario, Serializable>{

	public abstract Usuario findByUsername(String username);
	
	@Query(value = "SELECT * FROM usuarios WHERE ID_USUARIO IN\n" + 
			"(SELECT ID_USUARIO FROM usuarios_roles NATURAL JOIN roles WHERE AUTHORITY='ROLE_ADMIN')", nativeQuery = true)
	public abstract List<Usuario> findAdminUsers();
	
	@Query(value = "SELECT USERNAME FROM usuarios NATURAL JOIN usuarios_roles NATURAL JOIN roles WHERE AUTHORITY='ROLE_ADMIN' AND USERNAME=?1", nativeQuery = true)
	public abstract String isAdminUser(String username);
	
	@Modifying
	@Query("update Usuario u set u.password = :password where u.idUsuario = :id")
	public void updatePassword(@Param("password") String password, @Param("id") int id);
	
	public abstract Usuario findByEmail(String email);
}
