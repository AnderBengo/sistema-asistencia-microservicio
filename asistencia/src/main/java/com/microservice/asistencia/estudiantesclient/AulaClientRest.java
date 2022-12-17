package com.microservice.asistencia.estudiantesclient;

import com.microservice.asistencia.controller.dto.response.CustomResponse;
import com.microservice.asistencia.model.Aula;
import com.microservice.asistencia.model.Estudiante;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "servicio-aulas", url = "localhost:8002/api/aulas")
public interface AulaClientRest {

    @GetMapping("/buscar-aula-id/{id}")
    Aula buscarAula(@PathVariable Long id);

}
