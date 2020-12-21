package com.gpo7.proceso.servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.ElementoFormulario;
import com.gpo7.proceso.entity.Variable;
import com.gpo7.proceso.repository.ElementoFormularioJpaRepository;
import com.gpo7.proceso.servicio.ElementoFormularioService;

@Service("elementoFormularioServiceImpl")
public class ElementoFormularioServiceImpl implements ElementoFormularioService{

	@Autowired
	@Qualifier("elementoFormularioJpaRepository")
	private ElementoFormularioJpaRepository elementoFormularioJpaRepository;
	
	@Override
	public ElementoFormulario store(ElementoFormulario elementoFormulario) {
		return this.elementoFormularioJpaRepository.save(elementoFormulario);
	}

	@Override
	public void destroy(ElementoFormulario elementoFormulario) {
		this.elementoFormularioJpaRepository.delete(elementoFormulario);
	}

	@Override
	public ElementoFormulario findByVariable(Variable variable) {
		return this.elementoFormularioJpaRepository.findByVariable(variable);
	}

}
