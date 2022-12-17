package com.microservice.aula.estudiantesclient;

import com.microservice.aula.models.Estudiante;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "servicio-estudiantes", url = "localhost:8001/api/estudiantes")
public interface EstudiantesClientRest {

    @GetMapping("/por-aula/{codigoAula}")
    List<Estudiante> buscarEstudiantesPorAulaId(@PathVariable Long codigoAula);
}
