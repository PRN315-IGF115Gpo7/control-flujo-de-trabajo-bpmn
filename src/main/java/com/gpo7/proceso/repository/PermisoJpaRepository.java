package com.gpo7.proceso.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.Permiso;

@Repository("permisoJpaRepository")
public interface PermisoJpaRepository extends JpaRepository<Permiso, Serializable>{
	
}
