package com.example.OlSoftwarePrueba.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="comerciante")

public class ComercianteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String municipio;

    private String telefono;

    private String correo_electronico;

    private LocalDateTime fecha_registro;

    private String estado;

    private LocalDateTime fecha_actualizacion;

    private Long usuario;

}
