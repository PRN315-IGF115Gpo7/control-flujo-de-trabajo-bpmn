package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.repository.PrivilegioJpaRepository;
import com.gpo7.proceso.servicio.PrivilegioService;

@Service("privilegioServiceImpl")
public class PrivilegioServiceImpl implements PrivilegioService {

    @Autowired
    @Qualifier("privilegioJpaRepository")
    private PrivilegioJpaRepository privilegioJpaRepository;

	@Override
	public Privilegio store(Privilegio privilegio) {
		// TODO Auto-generated method stub
		return privilegioJpaRepository.save(privilegio);
	}

	@Override
	public Privilegio update(Privilegio privilegio) {
		// TODO Auto-generated method stub
		return privilegioJpaRepository.save(privilegio);
	}

	@Override
	public void destroy(Privilegio privilegio) {
		// TODO Auto-generated method stub
		privilegioJpaRepository.delete(privilegio);;
	}

	@Override
	public Privilegio findById(int idPrivilegio) {
		// TODO Auto-generated method stub
		return privilegioJpaRepository.findById(idPrivilegio).orElse(null);
	}

	@Override
	public List<Privilegio> getAll() {
		// TODO Auto-generated method stub
		return privilegioJpaRepository.findAll();
	}

	@Override
	public List<Privilegio> privilegiosNoAsignadosByRecurso(int idRecurso, int idRol) {
		// TODO Auto-generated method stub
		return privilegioJpaRepository.privilegiosNoAsignadosByRecurso(idRecurso, idRol);
	}

	@Override
	public List<Privilegio> privilegiosAsignadosByRecurso(int idRecurso, int idRol) {
		// TODO Auto-generated method stub
		return privilegioJpaRepository.privilegiosAsignadosByRecurso(idRecurso, idRol);
	}

	
}
