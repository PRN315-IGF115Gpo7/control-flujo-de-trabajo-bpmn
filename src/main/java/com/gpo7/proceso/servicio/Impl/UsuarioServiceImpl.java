package com.gpo7.proceso.servicio.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpo7.proceso.entity.Rol;
import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.repository.UserJpaRepository;
import com.gpo7.proceso.servicio.UsuarioService;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;

	@Override
	public List<Usuario> getAdminUsers() {
		// TODO Auto-generated method stub
		return userJpaRepository.findAdminUsers();
	}

	@Override
	public Boolean isAdminUser(String username) {
		// TODO Auto-generated method stub
		if(userJpaRepository.isAdminUser(username) != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return userJpaRepository.findByUsername(username);
	}
	public List<Usuario> getAll() {
		// TODO Auto-generated method stub
		return userJpaRepository.findAll();
	}

	@Override
	public Usuario update(Usuario usuario) {
		// TODO Auto-generated method stub
		return userJpaRepository.save(usuario);
	}

	@Override
	public Usuario findById(int idUsuario) {
		// TODO Auto-generated method stub
		return userJpaRepository.findById(idUsuario).orElse(null);
	}

		
}
