package com.example.OlSoftwarePrueba.service;

import com.example.OlSoftwarePrueba.reporitories.EstablecimientoRepository;

import com.example.OlSoftwarePrueba.request.ComercianteConsultaIdDTO;
import com.example.OlSoftwarePrueba.response.ResponseGenerarPdfDTO;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GenerarDocumentoServiceImpl {

    @Autowired
    EstablecimientoRepository establecimientoRepository;

    @Value("${patch.archivos}")
    String patchArchivo;

    @Autowired
    private ComercianteServiceImpl comercianteServiceImpl;

    public ResponseGenerarPdfDTO generarPdf(Long id) throws FileNotFoundException {

        ResponseGenerarPdfDTO respuesta;
        try {
            List<Object[]> lista = establecimientoRepository.consultarEstablecimientos(id);
            if (!lista.isEmpty()) {
                LocalDateTime hora = LocalDateTime.now();
                // Ruta donde se generará el archivo PDF
                String pdfPath = patchArchivo+"\\comerciante_" + hora.toString().replace(":", "") + ".pdf";

                // Crear el PdfWriter para escribir en el archivo
                PdfWriter writer = new PdfWriter(pdfPath);

                // Crear un nuevo documento PDF
                PdfDocument pdf = new PdfDocument(writer);
                pdf.setDefaultPageSize(PageSize.LETTER.rotate());
                Document document = new Document(pdf);

                // Definir los encabezados de la tabla y el número de columnas
                float[] columnWidths = {100, 50, 50, 50, 50, 50, 50, 50, 50, 50}; // Ancho de cada columna
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

                // Agregar datos a la tabla
                for (Object[] item : lista) {
                    table.addCell(new Cell().add(new Paragraph(item[0].toString())));
                    table.addCell(new Cell().add(new Paragraph(item[1].toString())));
                    table.addCell(new Cell().add(new Paragraph(item[2].toString())));
                    table.addCell(new Cell().add(new Paragraph(item[3].toString())));
                    table.addCell(new Cell().add(new Paragraph(item[4].toString())));
                    table.addCell(new Cell().add(new Paragraph(item[5].toString())));
                    table.addCell(new Cell().add(new Paragraph(item[6].toString())));
                    table.addCell(new Cell().add(new Paragraph(item[7].toString())));
                    table.addCell(new Cell().add(new Paragraph(item[8].toString())));
                    table.addCell(new Cell().add(new Paragraph(item[9].toString())));
                }

                // Agregar la tabla al documento
                document.add(table);

                // Cerrar el documento
                document.close();

                System.out.println("PDF creado exitosamente en: " + pdfPath);
                respuesta = ResponseGenerarPdfDTO.builder()
                        .status(true)
                        .message("PDF creado exitosamente en: " + pdfPath)
                        .archivo("")
                        .build();
            }else{
                respuesta = ResponseGenerarPdfDTO.builder()
                        .status(true)
                        .message("La consulta no arrojó resultados.")
                        .build();
            }

        }catch(Exception e){
            respuesta = ResponseGenerarPdfDTO.builder()
                    .status(false)
                    .message("Ha ocurrido un error generando el pdf.")
                    .build();
        }

        return respuesta;
    }



    public  ResponseGenerarPdfDTO generarCvs(Integer idComerciante) {

        LocalDateTime hora = LocalDateTime.now();
        String archivoCSV = patchArchivo+"\\comerciante_" + hora.toString().replace(":", "") + ".csv";
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        ResponseGenerarPdfDTO respuesta;

        try {
            // Crear el FileWriter para el archivo
            fileWriter = new FileWriter(archivoCSV);

            // Usar PrintWriter para escribir en el archivo CSV
            printWriter = new PrintWriter(fileWriter);

            // Escribir encabezado del archivo CSV
            printWriter.println("Nombre o razón social|Departamento|Municipio|" +
                    "Teléfono|Correo Electrónico|Fecha de Registro|Estado|Cantidad de Establecimientos|Total Activos|" +
                    "Cantidad de Empleados");

            // Escribir algunas filas de datos

            List<ComercianteConsultaIdDTO> lista = comercianteServiceImpl.consultarPorId(idComerciante);
            if(!lista.isEmpty()){
                for( ComercianteConsultaIdDTO t:lista) {
                    printWriter.println(t.getNombre()+"|"+
                            t.getDepartamento()+"|"+
                            t.getMunicipio()+"|"+
                            t.getTelefono()+"|"+
                            t.getEmail()+"|"+
                            t.getFecha_registro()+"|"+
                            t.getEstado()+"|"+
                            t.getNum_establecimientos()+"|"+
                            t.getActivos()+"|"+
                            t.getEmpleados());
                }

            }

            System.out.println("Archivo CSV creado correctamente.");

            respuesta = ResponseGenerarPdfDTO.builder()
                    .status(true)
                    .message("PDF creado exitosamente en: " + archivoCSV)
                    .archivo("")
                    .build();

        } catch ( Exception e) {
            respuesta = ResponseGenerarPdfDTO.builder()
                    .status(false)
                    .message("Ha ocurrido un error generando el CVS.")
                    .build();
        }  finally {
            // Asegurarse de cerrar los recursos
            if (printWriter != null) {
                printWriter.close();
            }
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return respuesta;
    }


}
