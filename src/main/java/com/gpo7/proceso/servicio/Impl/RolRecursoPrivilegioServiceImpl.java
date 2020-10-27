package com.gpo7.proceso.servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.repository.RolRecursoPrivilegioJpaRepository;
import com.gpo7.proceso.servicio.RolRecursoPrivilegioService;

@Service("rolRecursoPrivilegioServiceImpl")
public class RolRecursoPrivilegioServiceImpl implements RolRecursoPrivilegioService{
	
	@Autowired
	@Qualifier("rolRecursoPrivilegioJpaRepository")
	RolRecursoPrivilegioJpaRepository rolRecursoPrivilegioJpaRepository;

}
