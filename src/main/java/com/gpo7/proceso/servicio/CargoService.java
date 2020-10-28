package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Cargo;

public interface CargoService {
	public List<Cargo> getAll();
	public Cargo store(Cargo cargo);

}
