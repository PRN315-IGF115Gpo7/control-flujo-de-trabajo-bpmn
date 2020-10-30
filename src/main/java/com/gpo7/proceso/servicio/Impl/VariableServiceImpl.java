package com.gpo7.proceso.servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.Variable;
import com.gpo7.proceso.repository.VariableJpaRepository;
import com.gpo7.proceso.servicio.VariableService;

@Service("variableServiceImpl")
public class VariableServiceImpl implements VariableService{

	@Autowired
	@Qualifier("variableJpaRepository")
	private VariableJpaRepository variableJpaRepository;

	@Override
	public Variable store(Variable variable) {
		// TODO Auto-generated method stub
		return variableJpaRepository.save(variable);
	}
	
	
}
