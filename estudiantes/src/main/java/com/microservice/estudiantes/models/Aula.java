package com.microservice.estudiantes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Aula {

    private Long id;

    private String nombre;

    private List<Estudiante> estudiantes;
}
