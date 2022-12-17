package com.microservice.estudiantes.services;

import com.microservice.estudiantes.models.AsistenciaSimulacion;
import com.microservice.estudiantes.models.SimulacionAula;

import java.util.List;
import java.util.Optional;

public interface SimulacionAulaService {

    SimulacionAula crearSimulacion(SimulacionAula simulacionAula);
    Optional<SimulacionAula> buscarSimulacionAulaPorId(Long id);
    List<SimulacionAula> listarSimulacionAulaPorGrado(Long gradoId);
    AsistenciaSimulacion registrarAsistenciaSimulacion(AsistenciaSimulacion asistenciaSimulacion);
    AsistenciaSimulacion registrarSalidaSimulacion(AsistenciaSimulacion asistenciaSimulacion);
}
