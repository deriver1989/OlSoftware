package com.example.OlSoftwarePrueba.service;

import com.example.OlSoftwarePrueba.entities.EstablecimientoEntity;
import com.example.OlSoftwarePrueba.reporitories.EstablecimientoRepository;
import com.example.OlSoftwarePrueba.request.EstablecimientoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class EstablecimientoServiceImplTest {

    @Mock
    private EstablecimientoRepository establecimientoRepository;

    @InjectMocks
    private EstablecimientoServiceImpl establecimientoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardarEstablecimiento() {

        EstablecimientoDTO request = EstablecimientoDTO.builder()
                .id(15l)
                .nombre("prueba")
                .num_empleados(15l)
                .ingresos(50000d)
                .build();

        EstablecimientoEntity entidad = EstablecimientoEntity.builder()
                .id(15l)
                .nombre("prueba")
                .num_empleados(15l)
                .ingresos(50000d)
                .build();

        when(establecimientoRepository.save(any(EstablecimientoEntity.class))).thenReturn(entidad);
        var result = establecimientoService.guardarEstablecimiento(request);

        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void eliminarComerciante() {
        EstablecimientoEntity entidad = EstablecimientoEntity.builder()
                .id(15l)
                .nombre("prueba")
                .num_empleados(15l)
                .ingresos(50000d)
                .build();

        when(establecimientoRepository.findById(anyLong())).thenReturn(Optional.ofNullable(entidad));
        var result = establecimientoService.eliminarComerciante(1l);

        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void consultarPorId() {
        EstablecimientoEntity entidad = EstablecimientoEntity.builder()
                .id(15l)
                .nombre("prueba")
                .num_empleados(15l)
                .ingresos(50000d)
                .build();

        when(establecimientoRepository.establecimeintosPorComerciante(anyLong())).thenReturn(List.of(entidad));
        var result = establecimientoService.consultarPorId(12l);

        assertEquals(entidad.getId(), result.get(0).getId());
    }
}