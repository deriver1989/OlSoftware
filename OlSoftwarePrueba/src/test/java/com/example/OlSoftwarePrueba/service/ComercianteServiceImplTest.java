package com.example.OlSoftwarePrueba.service;

import com.example.OlSoftwarePrueba.entities.ComercianteEntity;
import com.example.OlSoftwarePrueba.reporitories.ComercianteRepository;
import com.example.OlSoftwarePrueba.request.ComercianteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class ComercianteServiceImplTest {

    @Mock
    private ComercianteRepository comercianteRepository;

    @InjectMocks
    private ComercianteServiceImpl comercianteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void guardarComerciante() {
        ComercianteDTO request = ComercianteDTO.builder()
                .id(15l)
                .nombre("prueba")
                .municipio("Cartagena")
                .telefono("3126304320")
                .estado("ACTIVO")
                .build();

        ComercianteEntity entidad = ComercianteEntity.builder()
                .id(15l)
                .nombre("prueba")
                .municipio("Cartagena")
                .telefono("3126304320")
                .estado("ACTIVO")
                .build();

        when(comercianteRepository.findById(anyLong())).thenReturn(Optional.of(entidad));
        when(comercianteRepository.save(any(ComercianteEntity.class))).thenReturn(entidad);
        var result = comercianteService.guardarComerciante(request);

        assertEquals(Boolean.TRUE, result);
    }


}