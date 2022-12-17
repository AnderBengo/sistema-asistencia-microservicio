package com.microservice.estudiantes.repository;

import com.microservice.estudiantes.models.AsistenciaSimulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AsistenciaSimulacionRepo extends JpaRepository<AsistenciaSimulacion, Long> {

    @Query("FROM AsistenciaSimulacion a WHERE a.estudiante.codigoEstudiante=?1")
    List<AsistenciaSimulacion> buscarPorCodigoEstudiante(String codigoEstudiante);
}
