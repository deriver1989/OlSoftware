package com.example.OlSoftwarePrueba.controller;


import com.example.OlSoftwarePrueba.request.ComercianteConsultaIdDTO;
import com.example.OlSoftwarePrueba.request.ComercianteDTO;
import com.example.OlSoftwarePrueba.response.ResponseDTO;
import com.example.OlSoftwarePrueba.service.ComercianteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComercianteController {

    @Autowired
    private ComercianteServiceImpl comercianteServiceImpl;


    @PostMapping("/comerciante/guardar")
    public ResponseDTO guardarComerciante(@Valid @RequestBody ComercianteDTO request){
        try {
            ResponseDTO response = null;
            if(comercianteServiceImpl.guardarComerciante(request)) {
                response = new ResponseDTO(200, "Guardado OK", true);
            }else{
                response = new ResponseDTO(200, "Error guardando comerciante", false);
            }
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al guardar el comerciante", null);
            return response;
        }
    }

    @PostMapping("/comerciante/actualizar")
    public ResponseDTO actualizarComerciante(@Valid @RequestBody ComercianteDTO request){
        try {
            ResponseDTO response = null;
            if(comercianteServiceImpl.actualizarComerciante(request)) {
                response = new ResponseDTO(200, "Guardado OK", true);
            }else{
                response = new ResponseDTO(200, "Error actualizando comerciante", false);
            }
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al actualizar el comerciante", null);
            return response;
        }
    }

    @GetMapping("/comerciante/eliminar/{id}")
    public ResponseDTO actualizarComerciante(@PathVariable Long id){
        try {
            ResponseDTO response = null;
            if(comercianteServiceImpl.eliminarComerciante(id)) {
                response = new ResponseDTO(200, "Eliminado OK", true);
            }else{
                response = new ResponseDTO(200, "Error eliminando comerciante", false);
            }
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al eliminar el comerciante", null);
            return response;
        }
    }


    @GetMapping("/comerciante/consultar/{pagina}/{cantidad}")
    public ResponseDTO consultarComerciante(@PathVariable Long pagina, @PathVariable Long cantidad){
        try {
            ResponseDTO response = null;
            List<Object[]> lista = comercianteServiceImpl.consultarComerciante();
            response = new ResponseDTO(200, "Consulta OK", lista);
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al eliminar el comerciante", null);
            return response;
        }
    }


    @GetMapping("/comerciante/consultar-id/{idComerciante}")
    public ResponseDTO consultarComerciante(@PathVariable Integer idComerciante){
        try {
            ResponseDTO response = null;
            List<ComercianteConsultaIdDTO> lista = comercianteServiceImpl.consultarPorId(idComerciante);
            response = new ResponseDTO(200, "Consulta OK", lista);
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al eliminar el comerciante", null);
            return response;
        }
    }

}
