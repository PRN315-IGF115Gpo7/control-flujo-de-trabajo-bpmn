package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.Permiso;
import com.gpo7.proceso.repository.PermisoJpaRepository;
import com.gpo7.proceso.servicio.PermisoService;

@Service("permisoServiceImpl")
public class PermisoServiceImpl implements PermisoService{
	
	@Autowired
	@Qualifier("permisoJpaRepository")
	private PermisoJpaRepository permisoJpaRepository;

	@Override
	public Permiso store(Permiso permiso) {
		// TODO Auto-generated method stub
		return permisoJpaRepository.save(permiso);
	}

	@Override
	public List<Permiso> getAll() {
		// TODO Auto-generated method stub
		return permisoJpaRepository.findAll();
	}

	@Override
	public void destroy(Permiso permiso) {
		// TODO Auto-generated method stub
		permisoJpaRepository.delete(permiso);
	}

	@Override
	public Permiso findById(int id_permiso) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
