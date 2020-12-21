package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.TipoElementoBpmn;

public interface TipoElementoService {
	
	public List<TipoElementoBpmn> getAll();
	public TipoElementoBpmn findById(int idTipoElementoBpmn);
	public TipoElementoBpmn findByNombre(String nombreTipoElementoBpmn);

}
