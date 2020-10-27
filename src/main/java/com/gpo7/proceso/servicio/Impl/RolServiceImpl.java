package com.gpo7.proceso.servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.repository.RolJpaRepository;
import com.gpo7.proceso.servicio.RolService;

@Service("rolServiceImpl")
public class RolServiceImpl implements RolService{
	
	@Autowired
	@Qualifier("rolJpaRepository")
	RolJpaRepository rolJpaRepository;

}
