package com.vaescode.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.vaescode.springboot.app.models.dao.IClienteDao;
import com.vaescode.springboot.app.models.entity.Cliente;

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

	@GetMapping("/form")
	public String crear(Map<String, Object> model) {

		Cliente cliente = new Cliente();
		model.put("titulo", "Formulario de cliente");
		model.put("cliente", cliente);
		return "form";
	}

	@PostMapping("/form")
	public String guardar(Cliente cliente) {
		clienteDao.save(cliente);
		return "redirect:listar";
	}

}
