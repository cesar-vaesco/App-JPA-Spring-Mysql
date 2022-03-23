package com.vaescode.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaescode.springboot.app.models.entity.Cliente;
import com.vaescode.springboot.app.models.entity.Factura;
import com.vaescode.springboot.app.models.entity.ItemFactura;
import com.vaescode.springboot.app.models.entity.Producto;
import com.vaescode.springboot.app.models.service.IClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

	private static final Logger log = LoggerFactory.getLogger(FacturaController.class);

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/ver/{id}")
	public String verFactura(@PathVariable(name = "id") Long id, Model model, RedirectAttributes flash) {

		Factura factura = clienteService.findFacturaById(id);

		if (factura == null) {
			flash.addFlashAttribute("error", "La factura no existe en la base de datos");
			return "redirect:/listar";
		}

		model.addAttribute("titulo", "factura: ".concat(factura.getDescripcion()));
		model.addAttribute("factura", factura);

		return "factura/ver";
	}

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

	@PostMapping("/form")
	public String guardar(@Valid Factura factura, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "crear factura");
			return "factura/form";
		}

		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "crear factura");
			model.addAttribute("error", "Error: la factura NO puede no tener líneas");

			return "factura/form";
		}

		for (int i = 0; i < itemId.length; i++) {
			Producto producto = clienteService.findProductoById(itemId[i]);

			ItemFactura linea = new ItemFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);

			factura.addItemsFactura(linea);

			log.info("Id: " + itemId[i].toString() + ", cantidad: " + cantidad);

		}

		clienteService.saveFactura(factura);

		status.setComplete();

		flash.addFlashAttribute("sucess", "Factura creada con éxito!");

		return "redirect:/ver/" + factura.getCliente().getId();
	}

	
	@GetMapping("/eliminar/{id}")
	public String eliminarFactura(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		Factura factura = clienteService.findFacturaById(id);
		
		if(factura != null) {
			clienteService.deleteFactura(id);
			flash.addFlashAttribute("success", "Factura eliminada con éxito!");
			return "redirect:/ver/" + factura.getCliente().getId();
		}
		flash.addFlashAttribute("error", "La factura no existe en la base de datos, no se pudo eliminar");
		
		return "redirect:/listar";
	}
	
}