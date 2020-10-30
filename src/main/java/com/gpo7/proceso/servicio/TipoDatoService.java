package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.TipoDato;

public interface TipoDatoService {

	public TipoDato findById(int tipoDatoId);
	public List<TipoDato> getAll();
}
