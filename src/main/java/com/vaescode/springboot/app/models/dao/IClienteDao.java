package com.vaescode.springboot.app.models.dao;

import java.util.List;

import com.vaescode.springboot.app.models.entity.Cliente;

public interface IClienteDao {
	
	public List<Cliente> findAll();

}
