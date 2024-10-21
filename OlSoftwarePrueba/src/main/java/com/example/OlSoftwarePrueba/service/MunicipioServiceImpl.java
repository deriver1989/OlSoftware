package com.example.OlSoftwarePrueba.service;

import com.example.OlSoftwarePrueba.entities.MunicipioEntity;
import com.example.OlSoftwarePrueba.reporitories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MunicipioServiceImpl {

    @Autowired
    private MunicipioRepository municipioRepository;

    public Iterable<MunicipioEntity> listaMunicipio (){
        return municipioRepository.findAll();
    }

}
