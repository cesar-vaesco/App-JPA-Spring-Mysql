package com.vaescode.springboot.app.view.csv;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("listar") // Nombre de la vista que queremos que se imprima en el texto
public class ClienteCsvView extends AbstractView {

	// constructor
	public ClienteCsvView() {
		setContentType("text/csv");
	}

	@Override // Método sobrescrito
	protected boolean generatesDownloadContent() {
		return true;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//Respuestas nombre del archivo descargado y el tipo
		response.setHeader("Content-Disposition", "attachment; filename=\"clientes.csv\"");
		response.setContentType(getContentType());
		
		

	}

}
