package com.example.OlSoftwarePrueba.service;

import com.example.OlSoftwarePrueba.request.ComercianteDTO;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class GenerarDocumentoServiceImpl {


    public Boolean generarPdf(Long id) throws FileNotFoundException {

        LocalDateTime hora = LocalDateTime.now();
        // Ruta donde se generará el archivo PDF
        String pdfPath = "C:\\archivo\\comerciante_"+hora.toString().replace(":","")+".pdf";

        // Crear el PdfWriter para escribir en el archivo
        PdfWriter writer = new PdfWriter(pdfPath);

        // Crear un nuevo documento PDF
        PdfDocument pdf = new PdfDocument(writer);
        pdf.setDefaultPageSize(PageSize.LETTER.rotate());
        Document document = new Document(pdf);

        // Definir los encabezados de la tabla y el número de columnas
        float[] columnWidths = {5, 2, 2, 2,2,2,2,2,2,2}; // Ancho de cada columna
        Table table = new Table(columnWidths);

        // Agregar las celdas de encabezado de la tabla
        table.addCell(new Cell().add(new Paragraph("Nombre o Razón Social")));
        table.addCell(new Cell().add(new Paragraph("Departamento")));
        table.addCell(new Cell().add(new Paragraph("Municipio")));
        table.addCell(new Cell().add(new Paragraph("Telefono")));
        table.addCell(new Cell().add(new Paragraph("Correo")));
        table.addCell(new Cell().add(new Paragraph("Fecha Registro")));
        table.addCell(new Cell().add(new Paragraph("Estado")));
        table.addCell(new Cell().add(new Paragraph("Num Establecimientos")));
        table.addCell(new Cell().add(new Paragraph("Total Activos")));
        table.addCell(new Cell().add(new Paragraph("Num Empleados")));

        // Agregar datos a la tabla (ejemplo)
        /*table.addCell(new Cell().add(new Paragraph("1")));
        table.addCell(new Cell().add(new Paragraph("John")));
        table.addCell(new Cell().add(new Paragraph("Doe")));
        table.addCell(new Cell().add(new Paragraph("$5000")));

        table.addCell(new Cell().add(new Paragraph("2")));
        table.addCell(new Cell().add(new Paragraph("Jane")));
        table.addCell(new Cell().add(new Paragraph("Smith")));
        table.addCell(new Cell().add(new Paragraph("$6000")));*/

        // Agregar la tabla al documento
        document.add(table);

        // Cerrar el documento
        document.close();

        System.out.println("PDF creado exitosamente en: " + pdfPath);

        return true;
    }



}
