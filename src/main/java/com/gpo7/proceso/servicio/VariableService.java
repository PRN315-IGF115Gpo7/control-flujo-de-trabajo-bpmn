package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Variable;

public interface VariableService {

	public Variable store(Variable variable);
	public Variable findById(int variableId);
}
