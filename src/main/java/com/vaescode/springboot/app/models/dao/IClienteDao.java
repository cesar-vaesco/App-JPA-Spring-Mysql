package com.vaescode.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vaescode.springboot.app.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
	public Cliente fetchByIdWithFacturas(Long id);
	
	/*
	 *  c -> alias de cliente
	 *  join fetch es un inner join en sql
	 *  la relación oneToMany uno a muchos en la clase Cliente permite llamar como atributo 
	 *  de cliente a facturas 
	 *  where -> cuando
	 * */ 
}
