package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Privilegio;

public interface PrivilegioService {
    public abstract List<Privilegio> getAll();
	public abstract Privilegio store(Privilegio privilegio);
    public abstract Privilegio update(Privilegio privilegio);
    public abstract void destroy(Privilegio privilegio);
    public Privilegio findById(int idPrivilegio);
    public abstract List<Privilegio> privilegiosNoAsignadosByRecurso(int idRecurso, int idRol);
	public abstract List<Privilegio> privilegiosAsignadosByRecurso(int idRecurso, int idRol);
}
