package com.vaescode.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vaescode.springboot.app.models.dao.IClienteDao;

@Controller
public class ClienteController {

	@Autowired
	private IClienteDao clienteDao;
	
	@GetMapping(value = "/listar")
	public String listar(Model model) {

		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("cliente", clienteDao.findAll());

		return "listar";
	}
}
