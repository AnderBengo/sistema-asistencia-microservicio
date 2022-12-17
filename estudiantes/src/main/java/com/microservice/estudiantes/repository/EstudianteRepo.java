package com.microservice.estudiantes.repository;

import com.microservice.estudiantes.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepo extends JpaRepository<Estudiante, Long> {

    @Query("FROM Estudiante e WHERE e.aulaId=?1")
    List<Estudiante> buscarEstudiantesPorAulaId(Long aulaId);

    @Query("FROM Estudiante e WHERE e.codigoEstudiante = ?1")
    Optional<Estudiante> buscarEstudiantePorCodigo(String codigoEstudiante);
}
