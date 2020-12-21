package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.TipoElementoBpmn;
import com.gpo7.proceso.repository.TipoElementoBpmnJpaRepository;
import com.gpo7.proceso.servicio.TipoElementoService;

@Service("tipoElementoServiceImpl")
public class TipoElementoServiceImpl implements TipoElementoService{
	
	@Autowired
	@Qualifier("tipoElementoBpmnJpaRepository")
	private TipoElementoBpmnJpaRepository tipoElementoBpmnJpaRepository;

	@Override
	public List<TipoElementoBpmn> getAll() {
		return tipoElementoBpmnJpaRepository.findAll();
	}

	@Override
	public TipoElementoBpmn findById(int idTipoElementoBpmn) {
		return tipoElementoBpmnJpaRepository.findById(idTipoElementoBpmn).get();
	}

	@Override
	public TipoElementoBpmn findByNombre(String nombreTipoElementoBpmn) {
		return tipoElementoBpmnJpaRepository.findByNombreTipoElementoBpmn(nombreTipoElementoBpmn);
	}

}
