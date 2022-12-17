package com.microservice.asistencia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaHoraIngreso;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaHoraSalida;
    private Boolean ingresoConfirmado;
    private Boolean salidaConfirmado;

    private Boolean inasistencia;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaQuery;

    private String codigoEstudiante;

    @Transient
    private Estudiante estudiante;
}
