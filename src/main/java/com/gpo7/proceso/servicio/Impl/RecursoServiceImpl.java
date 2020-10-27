package com.gpo7.proceso.servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.repository.RecursoJpaRepository;
import com.gpo7.proceso.servicio.RecursoService;

@Service("recursoServiceImpl")
public class RecursoServiceImpl implements RecursoService{
	
	@Autowired
	@Qualifier("recursoJpaRepository")
	RecursoJpaRepository recursoJpaRepository;

}
