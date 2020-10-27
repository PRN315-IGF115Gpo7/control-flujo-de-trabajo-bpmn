package com.gpo7.proceso.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpo7.proceso.entity.Usuario;

@Repository("userJpaRepository")
public interface UserJpaRepository extends JpaRepository<Usuario, Serializable>{

}
