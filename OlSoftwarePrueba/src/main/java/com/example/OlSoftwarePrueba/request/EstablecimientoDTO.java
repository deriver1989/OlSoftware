package com.example.OlSoftwarePrueba.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstablecimientoDTO {

    private Long id;

    private String nombre;

    private Double ingresos;

    private Long num_empleados;

    private Long comerciante_id;
}
