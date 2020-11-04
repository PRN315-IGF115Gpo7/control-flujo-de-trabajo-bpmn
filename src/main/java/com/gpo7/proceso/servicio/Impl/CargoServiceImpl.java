package com.gpo7.proceso.servicio.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.repository.CargoJpaRepository;
import com.gpo7.proceso.servicio.CargoService;
import com.gpo7.proceso.entity.Cargo;

@Service("cargoServiceImpl")
public class CargoServiceImpl implements CargoService {
	
	@Autowired
	@Qualifier("cargoJpaRepository")
	private CargoJpaRepository cargoJpaRepository;
	
	@Override
	public Cargo store(Cargo cargo) {
		// TODO Auto-generated method stub
		return cargoJpaRepository.save(cargo);
	}
	@Override
	public Cargo update(Cargo cargo) {
		// TODO Auto-generated method stub
		return cargoJpaRepository.save(cargo);
	}
	
	@Override
	public List<Cargo> getAll() {
		// TODO Auto-generated method stub
		return cargoJpaRepository.findAll();
	}
	@Override
	public void destroy(Cargo cargo) {
		// TODO Auto-generated method stub
		cargoJpaRepository.delete(cargo);
	}
	
	@Override
	public Cargo findById(int idCargo) {
		// TODO Auto-generated method stub
		return cargoJpaRepository.getOne(idCargo);
	}

}
