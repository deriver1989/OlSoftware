package com.example.OlSoftwarePrueba.service;


import com.example.OlSoftwarePrueba.entities.DepartamentoEntity;
import com.example.OlSoftwarePrueba.reporitories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoServiceImpl  {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public
    Iterable<DepartamentoEntity> listaDepartamento (){
        return departamentoRepository.findAll();
    }

}
