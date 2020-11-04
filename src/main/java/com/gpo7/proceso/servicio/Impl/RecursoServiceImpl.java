package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.repository.RecursoJpaRepository;
import com.gpo7.proceso.servicio.RecursoService;



import com.gpo7.proceso.entity.Recurso;

@Service("recursoServiceImpl")
public class RecursoServiceImpl implements RecursoService{
	
	@Autowired
	@Qualifier("recursoJpaRepository")
	RecursoJpaRepository recursoJpaRepository;
	
	@Override
	public List<Recurso> getAll() {
		// TODO Auto-generated method stub
		return recursoJpaRepository.findAll();
	}

	@Override
	public void destroy(Recurso recurso) {
		// TODO Auto-generated method stub
		recursoJpaRepository.delete(recurso);
	}
	
	@Override
	public Recurso findById(int id_recurso) {
		// TODO Auto-generated method stub
		return recursoJpaRepository.getOne(id_recurso);
	}

	@Override
	public Recurso store(Recurso recurso) {
		// TODO Auto-generated method stub
		return recursoJpaRepository.save(recurso);
	}

	@Override
	public Recurso update(Recurso recurso) {
		// TODO Auto-generated method stub
		return recursoJpaRepository.save(recurso);
	}

}
