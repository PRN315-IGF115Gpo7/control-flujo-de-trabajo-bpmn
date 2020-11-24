package com.gpo7.proceso.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.entity.Usuario;

@Repository("instanciaProcesoJpaRepository")
public interface InstanciaProcesoJpaRepository extends JpaRepository<InstanciaProceso, Serializable>{

	public abstract List<InstanciaProceso> findByUsuarioAndFinalizado(Usuario usuario, Boolean finalizado);
}
