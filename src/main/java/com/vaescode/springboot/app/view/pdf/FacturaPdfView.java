package com.vaescode.springboot.app.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.vaescode.springboot.app.models.entity.Factura;

@Component("factura/ver")
public class FacturaPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// CArgar los atributos del objeto factura
		Factura factura = (Factura) model.get("factura");

		PdfPTable tabla = new PdfPTable(1); // Numeros de columnas
		/* Agregando las filas que integran el documento */
		tabla.addCell("Datos del cliente");
		tabla.addCell(factura.getCliente().getNombre() + " " + factura.getCliente().getNombre());
		tabla.addCell(factura.getCliente().getEmail());

		PdfPTable tabla2 = new PdfPTable(1); // Numeros de columnas
		tabla.addCell("Datos de la factura");
		tabla.addCell("Folio: " + factura.getId());
		tabla.addCell("Descripción: " + factura.getDescripcion());
		tabla.addCell("Fecha: " + factura.getCreateAt());

		document.add(tabla);
		document.add(tabla2);
	}

}
