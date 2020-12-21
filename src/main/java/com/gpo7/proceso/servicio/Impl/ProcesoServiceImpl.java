package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.Proceso;
import com.gpo7.proceso.repository.ProcesoJpaRepository;
import com.gpo7.proceso.servicio.ProcesoService;

@Service("procesoServiceImpl")
public class ProcesoServiceImpl implements ProcesoService{

	@Autowired
	@Qualifier("procesoJpaRepository")
	private ProcesoJpaRepository procesoJpaRepository;
	
	@Override
	public List<Proceso> getAll() {
		// TODO Auto-generated method stub
		return procesoJpaRepository.findAll();
	}

	@Override
	public Proceso store(Proceso proceso) {
		// TODO Auto-generated method stub
		return procesoJpaRepository.save(proceso);
	}

	@Override
	public Proceso update(Proceso proceso) {
		// TODO Auto-generated method stub
		return procesoJpaRepository.save(proceso);
	}

	@Override
	public void destroy(Proceso proceso) {
		// TODO Auto-generated method stub
		procesoJpaRepository.delete(proceso);
	}

	@Override
	public Proceso findById(int proceso_id) {
		// TODO Auto-generated method stub
		return procesoJpaRepository.findById(proceso_id).orElse(null);
	}
}
