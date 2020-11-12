package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.Cargo;
import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Rol;
import com.gpo7.proceso.repository.RolJpaRepository;
import com.gpo7.proceso.servicio.RolService;

@Service("rolServiceImpl")
public class RolServiceImpl implements RolService{
	
	@Autowired
	@Qualifier("rolJpaRepository")
	RolJpaRepository rolJpaRepository;

	@Override
	public List<Rol> getAll() {
		// TODO Auto-generated method stub
		return rolJpaRepository.findAll();
	}
	
	public Rol store(Rol rol) {
		return rolJpaRepository.save(rol);
	}
	
	public Rol update(Rol rol) {
		return rolJpaRepository.save(rol);
	}
	
	public Rol findById(int idRol) {
		return rolJpaRepository.getOne(idRol);
	}
	@Override
	public void destroy(Rol rol) {
		// TODO Auto-generated method stub
		rolJpaRepository.delete(rol);
	}

	@Override
	public List<Rol> getAvailableRoles(int idUsuario) {
		// TODO Auto-generated method stub
		return rolJpaRepository.findAvailableRoles(idUsuario);
	}

	@Override
	public List<Rol> getUserRoles(int idUsuario) {
		// TODO Auto-generated method stub
		return rolJpaRepository.findUserRoles(idUsuario);
	}

	
}
