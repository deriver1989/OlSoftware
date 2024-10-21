package com.example.OlSoftwarePrueba.controller;


import com.example.OlSoftwarePrueba.entities.DepartamentoEntity;
import com.example.OlSoftwarePrueba.entities.MunicipioEntity;
import com.example.OlSoftwarePrueba.request.ResponseDTO;
import com.example.OlSoftwarePrueba.service.DepartamentoServiceImpl;
import com.example.OlSoftwarePrueba.service.MunicipioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ListasController {

    @Autowired
    private DepartamentoServiceImpl departamentoService;

    @Autowired
    private MunicipioServiceImpl municipioService;



    @GetMapping("/consultar-municipios")
    public ResponseDTO consultarMunicipios(){
        try {
            // Respuesta estándar en caso de éxito
            ResponseDTO response = new ResponseDTO(200, "Consulta OK", municipioService.listaMunicipio());
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al consultar los municipios", null);
            return response;
        }
    }

    @GetMapping("/consultar-departamentos")
    public ResponseDTO consultarDepartamentos(){
        try {
            // Respuesta estándar en caso de éxito
            ResponseDTO response = new ResponseDTO(200, "Consulta OK", departamentoService.listaDepartamento());
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al consultar los departamentos", null);
            return response;
        }
    }



}
