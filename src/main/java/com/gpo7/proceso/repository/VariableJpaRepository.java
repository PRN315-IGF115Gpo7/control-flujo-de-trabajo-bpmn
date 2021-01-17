package com.gpo7.proceso.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.entity.Variable;

@Repository("variableJpaRepository")
public interface VariableJpaRepository extends JpaRepository<Variable, Serializable>{

	public List<Variable> findByProceso(Proceso proceso);
}
