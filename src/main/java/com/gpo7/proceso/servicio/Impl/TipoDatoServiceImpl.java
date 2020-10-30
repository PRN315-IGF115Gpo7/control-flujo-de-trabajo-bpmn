package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.TipoDato;
import com.gpo7.proceso.repository.TipoDatoJpaRepository;
import com.gpo7.proceso.servicio.TipoDatoService;

@Service("tipoDatoServiceImpl")
public class TipoDatoServiceImpl implements TipoDatoService{

	@Autowired
	@Qualifier("tipoDatoJpaRepository")
	private TipoDatoJpaRepository tipoDatoJpaRepository;

	@Override
	public TipoDato findById(int tipoDatoId) {
		// TODO Auto-generated method stub
		return tipoDatoJpaRepository.findById(tipoDatoId).orElse(null);
	}

	@Override
	public List<TipoDato> getAll() {
		// TODO Auto-generated method stub
		return tipoDatoJpaRepository.findAll();
	}
	
}
