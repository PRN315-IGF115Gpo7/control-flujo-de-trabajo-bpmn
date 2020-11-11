package com.gpo7.proceso.servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Recurso;
import com.gpo7.proceso.entity.Rol;
import com.gpo7.proceso.entity.RolRecursoPrivilegio;
import com.gpo7.proceso.repository.RolRecursoPrivilegioJpaRepository;
import com.gpo7.proceso.servicio.RolRecursoPrivilegioService;

import java.util.List;

@Service("rolRecursoPrivilegioServiceImpl")
public class RolRecursoPrivilegioServiceImpl implements RolRecursoPrivilegioService {

    @Autowired
    @Qualifier("rolRecursoPrivilegioJpaRepository")
    private RolRecursoPrivilegioJpaRepository rolRecursoPrivilegioJpaRepository;

	@Override
	public RolRecursoPrivilegio store(RolRecursoPrivilegio rolRecursoPrivilegio) {
		// TODO Auto-generated method stub
		return rolRecursoPrivilegioJpaRepository.save(rolRecursoPrivilegio);
	}

	@Override
	public List<RolRecursoPrivilegio> findByRolAndRecurso(int idRecurso, int idRol) {
		// TODO Auto-generated method stub
		return rolRecursoPrivilegioJpaRepository.findByRolAndRecurso(idRecurso, idRol);
	}

	@Override
	public void destroyAll(List<RolRecursoPrivilegio> rrps) {
		// TODO Auto-generated method stub
		rolRecursoPrivilegioJpaRepository.deleteAll(rrps);
	}

}