package com.example.OlSoftwarePrueba.controller;


import com.example.OlSoftwarePrueba.entities.EstablecimientoEntity;
import com.example.OlSoftwarePrueba.request.ComercianteDTO;
import com.example.OlSoftwarePrueba.request.EstablecimientoDTO;
import com.example.OlSoftwarePrueba.request.ResponseDTO;
import com.example.OlSoftwarePrueba.service.ComercianteServiceImpl;
import com.example.OlSoftwarePrueba.service.EstablecimientoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EstablecimientoController {

    @Autowired
    private EstablecimientoServiceImpl establecimientoService;


    @PostMapping("/establecimiento/guardar")
    public ResponseDTO guardarEstablecimiento(@Valid @RequestBody EstablecimientoDTO request){
        try {
            ResponseDTO response = null;
            if(establecimientoService.guardarEstablecimiento(request)) {
                response = new ResponseDTO(200, "Guardado OK", true);
            }else{
                response = new ResponseDTO(200, "Error guardando establecimiento", false);
            }
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al guardar el establecimiento", null);
            return response;
        }
    }

    @PostMapping("/establecimiento/actualizar")
    public ResponseDTO actualizarEstablecimiento(@Valid @RequestBody EstablecimientoDTO request){
        try {
            ResponseDTO response = null;
            if(establecimientoService.actualizaEstablecimiento(request)) {
                response = new ResponseDTO(200, "Guardado OK", true);
            }else{
                response = new ResponseDTO(200, "Error actualizando establecimiento", false);
            }
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al actualizar el establecimiento", null);
            return response;
        }
    }

    @GetMapping("/establecimiento/eliminar/{id}")
    public ResponseDTO actualizarEstablecimiento(@PathVariable Long id){
        try {
            ResponseDTO response = null;
            if(establecimientoService.eliminarComerciante(id)) {
                response = new ResponseDTO(200, "Eliminado OK", true);
            }else{
                response = new ResponseDTO(200, "Error eliminando comerciante", false);
            }
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al eliminar el establecimiento", null);
            return response;
        }
    }


    @GetMapping("/establecimiento/consultar/{id}")
    public ResponseDTO consultarEstablecimiento(@PathVariable Long id){
        try {
            ResponseDTO response = null;
            List<EstablecimientoEntity> lista = establecimientoService.consultarPorId(id);
            response = new ResponseDTO(200, "Consulta OK", lista);
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al consultar el establecimiento", null);
            return response;
        }
    }

}
