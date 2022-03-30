package com.vaescode.springboot.app.view.csv;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.vaescode.springboot.app.models.entity.Cliente;

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

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//Respuestas nombre del archivo descargado y el tipo
		response.setHeader("Content-Disposition", "attachment; filename=\"clientes.csv\"");
		response.setContentType(getContentType());
		
		//Traer los objetos a mostrar
		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		
		ICsvBeanWriter beanWriter = new CsvBeanWriter(response.getWriter(),  CsvPreference.STANDARD_PREFERENCE);
		
		// Listar los atributos del objeto que se quiere mostrar en el archivo
		String[] header = {"id", "nombre", "apellido", "email", "createAt"};
		beanWriter.writeHeader(header);
		
		//Guardar el objeto en el texto
		for(Cliente cliente: clientes) {
			beanWriter.write(cliente, header);
		}
		
		beanWriter.close();

	}

}
