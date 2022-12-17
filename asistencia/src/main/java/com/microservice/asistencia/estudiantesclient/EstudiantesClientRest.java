package com.microservice.asistencia.estudiantesclient;

import com.microservice.asistencia.model.Estudiante;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "servicio-estudiantes", url = "localhost:8001/api/estudiantes")
public interface EstudiantesClientRest {

    @GetMapping("/por-codigo-estudiante/{codigoEstudiante}")
    Estudiante buscarEstudiantePorCodigo(@PathVariable String codigoEstudiante);

    @GetMapping("/por-aula/{codigoAula}")
    List<Estudiante> buscarEstudiantesPorAulaId(@PathVariable Long codigoAula);
}
