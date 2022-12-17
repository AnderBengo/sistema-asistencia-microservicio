package com.microservice.estudiantes.services;

import com.microservice.estudiantes.models.Estudiante;
import com.microservices.estudiantes.services.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    List<Estudiante> buscarEstudiantesPorAulaId(Long aulaId) throws ServiceException;
    Optional<Estudiante> buscarEstudiantePorCodigo(String codigoEstudiante);
}
