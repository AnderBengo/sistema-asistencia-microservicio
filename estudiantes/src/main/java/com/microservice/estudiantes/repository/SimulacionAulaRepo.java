package com.microservice.estudiantes.repository;

import com.microservice.estudiantes.models.SimulacionAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SimulacionAulaRepo extends JpaRepository<SimulacionAula, Long> {

    @Query("FROM SimulacionAula s WHERE s.gradoId=?1")
    List<SimulacionAula> listarSimulacionAulaPorGrado(Long gradoId);
}
