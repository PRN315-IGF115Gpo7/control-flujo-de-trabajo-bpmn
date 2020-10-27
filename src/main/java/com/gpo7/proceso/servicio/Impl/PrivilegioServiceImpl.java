package com.gpo7.proceso.servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.repository.PrivilegioJpaRepository;
import com.gpo7.proceso.servicio.PrivilegioService;

@Service("privilegioServiceImpl")
public class PrivilegioServiceImpl implements PrivilegioService {
	
	@Autowired
	@Qualifier("privilegioJpaRepository")
	private PrivilegioJpaRepository privilegioJpaRepository;

}
