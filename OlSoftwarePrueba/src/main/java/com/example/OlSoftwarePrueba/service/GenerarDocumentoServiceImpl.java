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



    public  ResponseGenerarPdfDTO generarCvs() {

        LocalDateTime hora = LocalDateTime.now();
        String archivoCSV = patchArchivo+"\\comerciante_" + hora.toString().replace(":", "") + ".csv";
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        ResponseGenerarPdfDTO respuesta;
        final String separador = "|";

        try {
            // Crear el FileWriter para el archivo
            fileWriter = new FileWriter(archivoCSV);

            // Usar PrintWriter para escribir en el archivo CSV
            printWriter = new PrintWriter(fileWriter);

            // Escribir encabezado del archivo CSV
            printWriter.println("Nombre o razón social"+separador+"Municipio" +separador+
                    "Teléfono"+separador+"Correo Electrónico"+separador+"Fecha de Registro"+separador+"Estado"+separador+
                    "Cantidad de Establecimientos"+separador+"Total Activos" +separador+ "Cantidad de Empleados");

            // Escribir algunas filas de datos

            List<ComercianteConsultaIdDTO> lista = comercianteServiceImpl.consultarComerciante();
            if(!lista.isEmpty()){
                for( ComercianteConsultaIdDTO t:lista) {
                    printWriter.println(t.getNombre()+separador+
                            t.getMunicipio()+separador+
                            t.getTelefono()+separador+
                            t.getEmail()+separador+
                            t.getFecha_registro()+separador+
                            t.getEstado()+separador+
                            t.getNum_establecimientos()+separador+
                            t.getActivos()+separador+
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
