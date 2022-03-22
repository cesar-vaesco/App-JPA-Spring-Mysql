package com.vaescode.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaescode.springboot.app.models.entity.Cliente;
import com.vaescode.springboot.app.models.entity.Factura;
import com.vaescode.springboot.app.models.entity.Producto;
import com.vaescode.springboot.app.models.service.IClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private IClienteService clienteService;

	// http://localhost:8080/factura/form/{clienteId}
	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value = "clienteId") Long clienteId, Map<String, Object> model,
			RedirectAttributes flash) {

		// Se busca el cliente
		Cliente cliente = clienteService.findOne(clienteId);

		// En caso de no encontrar el cliente
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		// Si se encuentra el cliente se crea una instancia de factura
		Factura factura = new Factura();
		factura.setCliente(cliente); // Asignación de un cliente a una factura

		// pasar información a la vista
		model.put("factura", factura);
		model.put("titulo", "Crear Factura");

		return "factura/form";
	}

	@GetMapping(value = "/cargar-productos/{term}", produces = { "application/json" })
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {

		return clienteService.findByNombre(term);
	}

}