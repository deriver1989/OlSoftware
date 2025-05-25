package com.example.OlSoftwarePrueba.controller;


import com.example.OlSoftwarePrueba.response.ResponseDTO;
import com.example.OlSoftwarePrueba.response.ResponseGenerarPdfDTO;
import com.example.OlSoftwarePrueba.service.GenerarDocumentoServiceImpl;
import com.example.OlSoftwarePrueba.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class DocumentoController {

    @Autowired
    private GenerarDocumentoServiceImpl generarDocumentoService;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/comerciante/gererar-cvs")
    public ResponseDTO generarCvs() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            ResponseDTO response = null;
            if(usuarioServiceImpl.esAdministrador(authentication)) {
                ResponseGenerarPdfDTO respuesta = generarDocumentoService.generarCvs();
                if (respuesta.getStatus()) {
                    response = new ResponseDTO(200, respuesta.getMessage(), respuesta);
                } else {
                    response = new ResponseDTO(200, respuesta.getMessage(), false);
                }
            }else{
                response = new ResponseDTO(200, "Error, el usuario no es administrador", false);
            }
            return response;
        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al generar el archivo pdf.", null);
            return response;
        }
    }




}
