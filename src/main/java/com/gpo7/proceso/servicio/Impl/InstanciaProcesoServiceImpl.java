package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.repository.InstanciaProcesoJpaRepository;
import com.gpo7.proceso.servicio.InstanciaProcesoService;

@Service("instanciaProcesoServiceImpl")
public class InstanciaProcesoServiceImpl implements InstanciaProcesoService{

	@Autowired
	@Qualifier("instanciaProcesoJpaRepository")
	private InstanciaProcesoJpaRepository instanciaProcesoJpaRepository;
	
	@Override
	public List<InstanciaProceso> findByUsuarioAndFinalizado(Usuario usuario, Boolean finalizado) {
		// TODO Auto-generated method stub
		return instanciaProcesoJpaRepository.findByUsuarioAndFinalizado(usuario, finalizado);
	}
}
