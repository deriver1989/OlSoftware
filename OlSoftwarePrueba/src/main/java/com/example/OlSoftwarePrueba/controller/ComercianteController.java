package com.example.OlSoftwarePrueba.controller;


import com.example.OlSoftwarePrueba.entities.ComercianteEntity;
import com.example.OlSoftwarePrueba.request.ComercianteConsultaIdDTO;
import com.example.OlSoftwarePrueba.request.ComercianteDTO;
import com.example.OlSoftwarePrueba.request.ComercianteFiltro;
import com.example.OlSoftwarePrueba.response.ResponseDTO;
import com.example.OlSoftwarePrueba.service.ComercianteServiceImpl;
import com.example.OlSoftwarePrueba.service.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComercianteController {

    @Autowired
    private ComercianteServiceImpl comercianteServiceImpl;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    //CREAR COMERCIANTE
    @PostMapping("/comerciante/crear")
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

    //ACTUALIZAR COMERCIANTE
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

    //ELIMINAR COMERCIANTE
    @GetMapping("/comerciante/eliminar/{id}")
    public ResponseDTO actualizarComerciante(@PathVariable Long id){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            ResponseDTO response = null;
            if(usuarioServiceImpl.esAdministrador(authentication)) {
                if (comercianteServiceImpl.eliminarComerciante(id)) {
                    response = new ResponseDTO(200, "Eliminado OK", true);
                } else {
                    response = new ResponseDTO(200, "Error eliminando comerciante", false);
                }
            }else{
                response = new ResponseDTO(200, "Error, el usuario no es administrador", false);
            }
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al eliminar el comerciante", null);
            return response;
        }
    }

    //CONSULTAR COMERCIANTE PAGINADO
    @GetMapping("/comerciante/consultar")
    public ResponseDTO consultarComerciante(ComercianteFiltro filtro,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size){
        try {
            ResponseDTO response = null;
            Pageable pg = PageRequest.of(page, size, Sort.by("nombre"));
            Page<ComercianteEntity> respuesta = comercianteServiceImpl.consultarComerciantePaginado(filtro, pg);
            response = new ResponseDTO(200, "Consulta OK", respuesta);
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al eliminar el comerciante", null);
            return response;
        }
    }

    //CONSULTAR POR ID
    @GetMapping("/comerciante/consultar-id/{idComerciante}")
    public ResponseDTO consultarComerciante(@PathVariable Long idComerciante){
        try {
            ResponseDTO response = null;
            ComercianteEntity c = comercianteServiceImpl.consultarPorId(idComerciante);
            response = new ResponseDTO(200, (c==null) ?"Consulta no trajo resultados" :"Consulta OK", c);
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al consultar el comerciante", null);
            return response;
        }
    }

    //CAMBIAR ESTADO
    @PatchMapping("/comerciante/{id}/estado")
    public ResponseDTO patchEstado(@PathVariable Long id,@RequestParam String estado) {
        try {
            ResponseDTO response = null;
            Boolean respuesta = comercianteServiceImpl.cambiarEstado(id, estado);
            response = new ResponseDTO(200, "Cambio de estado OK", respuesta);
            return response;

        } catch (Exception e) {
            // Respuesta estándar en caso de error
            ResponseDTO response = new ResponseDTO(404, "Error al cambiar el estado del comerciante.", null);
            return response;
        }
    }

}
