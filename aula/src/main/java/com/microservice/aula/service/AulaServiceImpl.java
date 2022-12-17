package com.microservice.aula.service;

import com.microservice.aula.estudiantesclient.EstudiantesClientRest;
import com.microservice.aula.models.Aula;
import com.microservice.aula.models.Estudiante;
import com.microservice.aula.models.Grado;
import com.microservice.aula.repository.AulaRepo;
import com.microservice.aula.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AulaServiceImpl implements AulaService {

    private final AulaRepo aulaRepo;

    @Autowired
    private EstudiantesClientRest estudiantesClientRest;

    @Override
    public Aula guardar(Aula aula) throws ServiceException {
        try {
            return aulaRepo.save(aula);
        } catch (DataAccessException e) {
            throw new ServiceException("Error en la base de datos al insertar el aula.");
        }
    }

    @Override
    public Optional<Aula> buscarPorAulaId(Long aulaId) {
        Optional<Aula> aulaEncontrada = aulaRepo.findById(aulaId);

        aulaEncontrada.ifPresent(aula -> aula.setEstudiantes(estudiantesClientRest.buscarEstudiantesPorAulaId(aulaId)));

        return aulaEncontrada;
    }

    @Override
    public List<Aula> listarAulas() {
        List<Aula> aulas = aulaRepo.findAll();

        return aulas.stream().map(aula -> {
            List<Estudiante> estudiantes = estudiantesClientRest.buscarEstudiantesPorAulaId(aula.getId());
            aula.setEstudiantes(estudiantes);
            return aula;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Grado> buscarGradoPorId(Long id) {
        Optional<Grado> grado = aulaRepo.buscarGradoPorId(id);
        if(grado.isPresent()) {
            List<Aula> aulas = grado.get().getAulas().stream().map(aula -> {
                List<Estudiante> estudiantes = estudiantesClientRest.buscarEstudiantesPorAulaId(aula.getId());
                aula.setEstudiantes(estudiantes);
                return aula;
            }).collect(Collectors.toList());
            grado.get().setAulas(aulas);
        }
        return grado;
    }

    @Override
    public List<Grado> listarGrados() {
        List<Grado> grados = aulaRepo.listarGrados();

        return grados.stream().map(grado -> {
            List<Aula> aulas = grado.getAulas().stream().map(aula -> {
                List<Estudiante> estudiantes = estudiantesClientRest.buscarEstudiantesPorAulaId(aula.getId());
                aula.setEstudiantes(estudiantes);
                return aula;
            }).collect(Collectors.toList());
            grado.setAulas(aulas);
            return grado;
        }).collect(Collectors.toList());
    }
}
