package com.gpo7.proceso.servicio.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.repository.UserJpaRepository;
import com.gpo7.proceso.servicio.UsuarioService;

@Service("usuarioServicioImpl")
public class UsuarioServicioImpl implements UsuarioService{

	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	
}
