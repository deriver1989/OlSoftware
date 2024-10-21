package com.example.OlSoftwarePrueba.service;

import com.example.OlSoftwarePrueba.entities.EstablecimientoEntity;
import com.example.OlSoftwarePrueba.reporitories.EstablecimientoRepository;
import com.example.OlSoftwarePrueba.request.EstablecimientoDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstablecimientoServiceImpl {

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    @Transactional
    public Boolean guardarEstablecimiento(EstablecimientoDTO request){
        establecimientoRepository.save(EstablecimientoEntity.builder()
                .nombre(request.getNombre())
                .ingresos(request.getIngresos())
                .num_empleados(request.getNum_empleados())
                .comerciante_id(request.getComerciante_id())
                .build());
        return true;
    }

    @Transactional
    public Boolean actualizaEstablecimiento(EstablecimientoDTO request){
        EstablecimientoEntity establecimientoEntity= establecimientoRepository.findById(request.getId()).orElse(null);
        if(establecimientoEntity != null){
            establecimientoEntity.setNombre(request.getNombre());
            establecimientoEntity.setIngresos(request.getIngresos());
            establecimientoEntity.setNum_empleados(request.getNum_empleados());
            establecimientoEntity.setComerciante_id(request.getComerciante_id());
            establecimientoRepository.save(establecimientoEntity);
        }
        return true;
    }


    @Transactional
    public Boolean eliminarComerciante(Long request){
        EstablecimientoEntity establecimientoEntity= establecimientoRepository.findById(request).orElse(null);
        if(establecimientoEntity != null){
            establecimientoRepository.delete(establecimientoEntity);
        }
        return true;
    }

    public List<EstablecimientoEntity> consultarPorId(Long id){
        return establecimientoRepository.establecimeintosPorComerciante(id);
    }


}
