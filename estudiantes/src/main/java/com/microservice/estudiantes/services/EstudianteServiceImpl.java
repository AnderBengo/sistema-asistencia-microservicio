package com.microservice.estudiantes.services;

import com.microservice.estudiantes.models.Estudiante;
import com.microservice.estudiantes.repository.EstudianteRepo;
import com.microservices.estudiantes.services.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepo estudianteRepo;

    @Override
    public List<Estudiante> buscarEstudiantesPorAulaId(Long aulaId) throws ServiceException {
        if(aulaId==null){
            throw new ServiceException("El aulaId no puede ser nulo");
        }
        return estudianteRepo.buscarEstudiantesPorAulaId(aulaId);
    }

    @Override
    public Optional<Estudiante> buscarEstudiantePorCodigo(String codigoEstudiante) {
        return estudianteRepo.buscarEstudiantePorCodigo(codigoEstudiante);
    }
}
