package com.example.OlSoftwarePrueba.controller;


import com.example.OlSoftwarePrueba.response.ResponseDTO;
import com.example.OlSoftwarePrueba.service.DepartamentoServiceImpl;
import com.example.OlSoftwarePrueba.service.MunicipioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
