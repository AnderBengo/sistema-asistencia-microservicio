package com.microservice.estudiantes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SimulacionAulaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name="aula_simulacion_examen_asistencia",
            joinColumns=
            @JoinColumn(name="aula_simulacion_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="asistencia_estudiante_id", referencedColumnName="id"))
    private List<AsistenciaSimulacion> asistenciasSimulacion;
}
