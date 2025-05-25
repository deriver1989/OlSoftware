package com.example.OlSoftwarePrueba.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComercianteFiltro {
    private String nombre;
    private LocalDate fechaRegistroDesde;
    private LocalDate fechaRegistroHasta;
    private String estado;
    // getters y setters
}