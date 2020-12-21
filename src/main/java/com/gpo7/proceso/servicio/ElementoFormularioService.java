package com.gpo7.proceso.servicio;

import com.gpo7.proceso.entity.ElementoFormulario;
import com.gpo7.proceso.entity.Variable;

public interface ElementoFormularioService {

	public ElementoFormulario store(ElementoFormulario elementoFormulario);
	public void destroy(ElementoFormulario elementoFormulario);
	public ElementoFormulario findByVariable(Variable variable);
}
