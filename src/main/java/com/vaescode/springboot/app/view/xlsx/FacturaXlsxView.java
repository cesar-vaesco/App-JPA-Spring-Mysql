package com.vaescode.springboot.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.vaescode.springboot.app.models.entity.Factura;

@Component("factura/ver.xlsx")
public class FacturaXlsxView extends  AbstractXlsxView  {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Obtener la factura de la igual forma que se obtiene en el controlador
		Factura factura = (Factura) model.get("factura");
		// Hojas de excel -
		Sheet sheet = workbook.createSheet("Factura Spring");
		// primer fila
		Row row = sheet.createRow(0);
		// Celda
		Cell cell = row.createCell(0);
		cell.setCellValue("Datos del cliente");

		// segunda fila
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());

		// tercer fila
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getEmail());

		// Fila 3 no existe para dar espacio
		// Forma diferente de hacer fila
		sheet.createRow(4).createCell(0).setCellValue("Datos de la factura");
		sheet.createRow(5).createCell(0).setCellValue("Folio: " + factura.getId());
		sheet.createRow(6).createCell(0).setCellValue("Descripción: " + factura.getDescripcion());
		sheet.createRow(7).createCell(0).setCellValue("Fecha: " + factura.getCreateAt());

	}

}
