package com.vaescode.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.vaescode.springboot.app.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>  {

}
