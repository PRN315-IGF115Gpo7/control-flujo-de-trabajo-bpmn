package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.ElementoBpmnFormulario;
import com.gpo7.proceso.entity.InstanciaActividad;
import com.gpo7.proceso.entity.Respuesta;
import com.gpo7.proceso.repository.RespuestaJpaRepository;
import com.gpo7.proceso.servicio.RespuestaService;

@Service("respuestaServiceImpl")
public class RespuestaServiceImpl implements RespuestaService{

	@Autowired
	@Qualifier("respuestaRepository")
	private RespuestaJpaRepository respuestaJpaRepository;
	
	@Override
	public Respuesta store(Respuesta respuesta) {
		// TODO Auto-generated method stub
		return respuestaJpaRepository.save(respuesta);
	}

	@Override
	public Respuesta findByInstanciaActividadAndElementoBpmnFormulario(InstanciaActividad ia,
			ElementoBpmnFormulario ebf) {
		// TODO Auto-generated method stub
		return respuestaJpaRepository.findByInstanciaActividadAndElementoBpmnFormulario(ia, ebf);
	}

	@Override
	public List<Respuesta> findByInstanciaActividad(InstanciaActividad instanciaActividad) {
		// TODO Auto-generated method stub
		return this.respuestaJpaRepository.findByInstanciaActividad(instanciaActividad);
	}
}
