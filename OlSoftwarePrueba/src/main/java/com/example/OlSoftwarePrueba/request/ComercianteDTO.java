package com.example.OlSoftwarePrueba.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComercianteDTO {

    @Email
    private String email;

    @NotBlank
    private String nombre;

    @NotBlank
    private String departamento;

    @NotBlank
    private String municipio;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fecha_registro;

    @NotBlank
    private String estado;

    private String telefono;

    private Long id;
}
