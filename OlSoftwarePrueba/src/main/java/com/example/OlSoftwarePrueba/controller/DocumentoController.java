package com.example.OlSoftwarePrueba.controller;


import com.example.OlSoftwarePrueba.request.ComercianteDTO;
import com.example.OlSoftwarePrueba.request.ResponseDTO;
import com.example.OlSoftwarePrueba.service.ComercianteServiceImpl;
import com.example.OlSoftwarePrueba.service.GenerarDocumentoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentoController {

    @Autowired
    private GenerarDocumentoServiceImpl generarDocumentoService;


    @GetMapping("/comerciante/gererar-pdf/{id}")
    public ResponseDTO actualizarComerciante(@PathVariable Long id){
        try {
            ResponseDTO response = null;
            if(generarDocumentoService.generarPdf(id)) {
                response = new ResponseDTO(200, "Generado OK", true);
            }else{
                response = new ResponseDTO(200, "Error generardo pdf de comerciante", false);
            }
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al eliminar el comerciante", null);
            return response;
        }
    }



}
