package com.example.OlSoftwarePrueba.controller;


import com.example.OlSoftwarePrueba.response.ResponseDTO;
import com.example.OlSoftwarePrueba.response.ResponseGenerarPdfDTO;
import com.example.OlSoftwarePrueba.service.GenerarDocumentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DocumentoController {

    @Autowired
    private GenerarDocumentoServiceImpl generarDocumentoService;


    @GetMapping("/comerciante/gererar-pdf/{idComerciante}")
    public ResponseDTO actualizarComerciante(@PathVariable Long idComerciante){
        try {
            ResponseDTO response = null;
            ResponseGenerarPdfDTO respuesta = generarDocumentoService.generarPdf(idComerciante);
            if(respuesta.getStatus()) {
                response = new ResponseDTO(200, respuesta.getMessage(), respuesta);
            }else{
                response = new ResponseDTO(200, respuesta.getMessage(), false);
            }
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al generar el archivo pdf.", null);
            return response;
        }
    }



}
