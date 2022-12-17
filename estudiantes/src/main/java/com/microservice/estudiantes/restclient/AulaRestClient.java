package com.microservice.estudiantes.restclient;

import com.microservice.estudiantes.models.Grado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "servicio-aulas", url = "localhost:8002/api/aulas")
public interface AulaRestClient {

    @GetMapping("/grado/{gradoId}")
    Grado buscarGradoPorId(@PathVariable Long gradoId);
}
