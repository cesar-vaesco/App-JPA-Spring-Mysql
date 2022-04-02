package com.vaescode.springboot.app.view.json;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.vaescode.springboot.app.models.entity.Cliente;

@Component("listar.json")
public class ClienteListJsonView extends MappingJackson2JsonView {

	@Override
	protected Object filterModel(Map<String, Object> model) {
		// Quitando onjetos que no queremos del model que se usa en la vista listar en
		// el controllador Cliente

		model.remove("titulo");
		model.remove("page");

		// Remover la páginación para que no se muestre el json
		@SuppressWarnings("unchecked")
		//Mostrar todos los registrps en formato json
		Page<Cliente> clientes = new PageImpl<Cliente>((List<Cliente>) model.get("clientes"));
		// -> mostrar primeros registros Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		
		model.remove("clientes");
		model.put("clientes", clientes.getContent());

		return super.filterModel(model);
	}

}
