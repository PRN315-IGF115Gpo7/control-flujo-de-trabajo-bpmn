package com.gpo7.proceso.servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.ElementoBpmn;
import com.gpo7.proceso.entity.InstanciaActividad;
import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.repository.InstanciaActividadJpaRepository;
import com.gpo7.proceso.servicio.InstanciaActividadService;

@Service("instanciaActividadServiceImpl")
public class InstanciaActividadServiceImpl implements InstanciaActividadService{

	@Autowired
	@Qualifier("instanciaActividadJpaRepository")
	private InstanciaActividadJpaRepository instanciaActividadJpaRepository;
	
	@Override
	public InstanciaActividad store(InstanciaActividad instanciaAcitvidad) {
		// TODO Auto-generated method stub
		return instanciaActividadJpaRepository.save(instanciaAcitvidad);
	}

	@Override
	public InstanciaActividad getCurrActivity(Boolean finalizada, Integer instanciaProcId) {
		// TODO Auto-generated method stub
		return instanciaActividadJpaRepository.getCurrActivity(finalizada, instanciaProcId);
	}

	@Override
	public InstanciaActividad findById(Integer idInstancia) {
		// TODO Auto-generated method stub
		return instanciaActividadJpaRepository.findById(idInstancia).orElse(null);
	}

	@Override
	public InstanciaActividad update(InstanciaActividad instanciaAcitvidad) {
		// TODO Auto-generated method stub
		return instanciaActividadJpaRepository.save(instanciaAcitvidad);
	}

	@Override
	public InstanciaActividad findByElementoBpmnAndInstanciaProceso(ElementoBpmn eb, InstanciaProceso ip) {
		// TODO Auto-generated method stub
		return instanciaActividadJpaRepository.findByElementoBpmnAndInstanciaProceso(eb, ip);
	}

	@Override
	public InstanciaActividad getActivityByCargo(int cargoId, int instanciaProcesoId) {
		// TODO Auto-generated method stub
		return instanciaActividadJpaRepository.getActivityByCargo(cargoId, instanciaProcesoId);
	}
}
