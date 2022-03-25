package com.vaescode.springboot.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaescode.springboot.app.models.dao.IUsuarioDao;
import com.vaescode.springboot.app.models.entity.Role;
import com.vaescode.springboot.app.models.entity.Usuario;

@Service("jpaUserDetailsService ")
public class JpaUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.findByUsername(username);

		if (usuario == null) {
			logger.info("No existe el usuario: " + username);

			throw new UsernameNotFoundException("El usuario '" + username + "' no existe en el sistema!");
		}

		// Obtener roles

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : usuario.getRoles()) {
			logger.info("Role: " .concat(role.getAuthority()));;
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}

		if (authorities.isEmpty()) {
			logger.error("ERROR login: el usuario '" + username + "' no tiene role asignado");

			throw new UsernameNotFoundException("ERROR login: el usuario '" + username + "' no tiene role asignado");
		}

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

}
