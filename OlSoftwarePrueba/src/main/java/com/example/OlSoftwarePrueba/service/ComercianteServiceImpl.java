package com.example.OlSoftwarePrueba.service;

import com.example.OlSoftwarePrueba.request.ComercianteDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComercianteServiceImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Boolean guardarComerciante(ComercianteDTO request){
        StoredProcedureQuery storedProcedure = entityManager
                .createStoredProcedureQuery("PKS_COMERCIANTE.crear_comerciante");

        // Registrar los parámetros de entrada
        storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(4, Date.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(6, Long.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter(7, String.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);

        // Establecer los valores de los parámetros
        storedProcedure.setParameter(1, request.getNombre());
        storedProcedure.setParameter(2, request.getDepartamento());
        storedProcedure.setParameter(3, request.getMunicipio());
        storedProcedure.setParameter(4, request.getFecha_registro());
        storedProcedure.setParameter(5, request.getEstado());
        storedProcedure.setParameter(8, request.getTelefono());
        storedProcedure.setParameter(9, request.getEmail());

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        Long estado = (Long) storedProcedure.getOutputParameterValue(6);
        String mensaje = (String) storedProcedure.getOutputParameterValue(7);

        if (estado == 0){
            return true;
        }else{
            return false;
        }

    }

    @Transactional
    public Boolean actualizarComerciante(ComercianteDTO request){
        StoredProcedureQuery storedProcedure = entityManager
                .createStoredProcedureQuery("PKS_COMERCIANTE.actualizar_comerciante");

        // Registrar los parámetros de entrada
        storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(5, Date.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(7, Long.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter(8, String.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(10, String.class, ParameterMode.IN);

        // Establecer los valores de los parámetros
        storedProcedure.setParameter(1, request.getId());
        storedProcedure.setParameter(2, request.getNombre());
        storedProcedure.setParameter(3, request.getDepartamento());
        storedProcedure.setParameter(4, request.getMunicipio());
        storedProcedure.setParameter(5, request.getFecha_registro());
        storedProcedure.setParameter(6, request.getEstado());
        storedProcedure.setParameter(9, request.getTelefono());
        storedProcedure.setParameter(10, request.getEmail());

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        Long estado = (Long) storedProcedure.getOutputParameterValue(6);
        String mensaje = (String) storedProcedure.getOutputParameterValue(7);

        if (estado == 0){
            return true;
        }else{
            return false;
        }

    }

    public List consultarComerciante(){

        return null;
    }


    @Transactional
    public Boolean eliminarComerciante(Long request){
        StoredProcedureQuery storedProcedure = entityManager
                .createStoredProcedureQuery("PKS_COMERCIANTE.eliminar_comerciante");

        // Registrar los parámetros de entrada
        storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter(2, Long.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);

        // Establecer los valores de los parámetros
        storedProcedure.setParameter(1,request);

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        Long estado = (Long) storedProcedure.getOutputParameterValue(2);
        String mensaje = (String) storedProcedure.getOutputParameterValue(3);
        System.out.println(mensaje);
        if (estado == 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean consultarPorId(Integer id){
        return true;
    }


}
