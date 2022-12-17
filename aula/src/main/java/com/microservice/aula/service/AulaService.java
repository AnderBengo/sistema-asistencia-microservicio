package com.microservice.aula.service;

import com.microservice.aula.models.Aula;
import com.microservice.aula.models.Grado;
import com.microservice.aula.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface AulaService {

    Aula guardar(Aula aula) throws ServiceException;
    Optional<Aula> buscarPorAulaId(Long aulaId);
    List<Aula> listarAulas();
    Optional<Grado> buscarGradoPorId(Long id);
    List<Grado> listarGrados();
}
