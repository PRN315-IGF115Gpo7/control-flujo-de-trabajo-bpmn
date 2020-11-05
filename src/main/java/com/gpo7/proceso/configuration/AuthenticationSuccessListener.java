package com.gpo7.proceso.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.gpo7.proceso.entity.Usuario;
import com.gpo7.proceso.repository.UserJpaRepository;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent>{

	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent evt) {
		// TODO Auto-generated method stub
		String username = evt.getAuthentication().getName();
		Usuario usuario  = userJpaRepository.findByUsername(username);
		usuario.setIntentos(0);
		userJpaRepository.save(usuario);
		
	}

}
