package com.gpo7.proceso.servicio;

import java.util.List;

import com.gpo7.proceso.entity.Privilegio;
import com.gpo7.proceso.entity.Recurso;
import com.gpo7.proceso.entity.Rol;
import com.gpo7.proceso.entity.RolRecursoPrivilegio;

public interface RolRecursoPrivilegioService {

    public abstract RolRecursoPrivilegio store(RolRecursoPrivilegio rolRecursoPrivilegio);
    public abstract List<RolRecursoPrivilegio> findByRolAndRecurso(int idRecurso, int idRol);
    public abstract void destroyAll(List<RolRecursoPrivilegio> rrps);

}