package com.microservice.aula.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estudiante {

    private Long id;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String codigoEstudiante;
    private Long aulaId;
}
