package com.microservice.asistencia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Aula {

    private Long id;

    private String nombre;



}
