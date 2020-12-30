package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gpo7.proceso.entity.InstanciaProceso;
import com.gpo7.proceso.entity.Proceso;
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

	public List<InstanciaProceso> getAll() {
		// TODO Auto-generated method stub
		return instanciaProcesoJpaRepository.findAll();
	}

	@Override
	public List<InstanciaProceso> findByProcesoAndFinalizado(Proceso proceso, Boolean finalizado) {
		// TODO Auto-generated method stub
		return instanciaProcesoJpaRepository.findByProcesoAndFinalizado(proceso, finalizado);
	}

	@Override
	public InstanciaProceso findByProcesoAndUsuario(Proceso proceso, Usuario usuario) {
		// TODO Auto-generated method stub
		return instanciaProcesoJpaRepository.findByProcesoAndUsuario(proceso, usuario);
	}

	@Override
	public InstanciaProceso store(InstanciaProceso instanciaProceso) {
		// TODO Auto-generated method stub
		return instanciaProcesoJpaRepository.save(instanciaProceso);
	}

	@Override
	public InstanciaProceso update(InstanciaProceso instanciaProceso) {
		// TODO Auto-generated method stub
		return instanciaProcesoJpaRepository.save(instanciaProceso);
	}

}
