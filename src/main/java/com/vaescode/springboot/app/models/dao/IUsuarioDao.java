package com.vaescode.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.vaescode.springboot.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	public Usuario findByUsername(String username);

	/*
	 * Otra opción
	 * 
	 * 
	 * @Query("select u from Usuario u where u.username=?1") 
	 * public Usuario fetchByUsername(String username);
	 */

}
