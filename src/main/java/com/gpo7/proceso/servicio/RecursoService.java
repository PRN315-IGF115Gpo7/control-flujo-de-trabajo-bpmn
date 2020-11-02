package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Recurso;



public interface RecursoService {

	public List<Recurso> getAll();
	public Recurso store(Recurso recurso);
	public void destroy(Recurso recurso);
	public Recurso findById(int id_recurso);
}
