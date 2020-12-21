package com.gpo7.proceso.servicio;

import java.util.ArrayList;
import java.util.List;

import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.ElementoBpmnFormulario;

public interface ElementoBpmnFormularioService {

	public ElementoBpmnFormulario store(ElementoBpmnFormulario elementoBpmnFormulario);
	public ElementoBpmnFormulario update(ElementoBpmnFormulario elementoBpmnFormulario);
	public void destroy(ElementoBpmnFormulario elementoBpmnFormulario);
	public List<ElementoBpmnFormulario> index();
	public List<ElementoBpmnFormulario> findByElementoBpmn(ElementoBpmn elementoBpmn);
}
