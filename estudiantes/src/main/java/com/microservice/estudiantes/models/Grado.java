package com.microservice.estudiantes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Grado {
    private Long id;
    private String nombre;
    private List<Aula> aulas;
}
