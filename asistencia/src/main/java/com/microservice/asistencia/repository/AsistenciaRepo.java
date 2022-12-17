package com.microservice.asistencia.repository;

import com.microservice.asistencia.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepo extends JpaRepository<Asistencia, Long> {

    Optional<Asistencia> findByCodigoEstudianteAndFechaQuery(String codigoEstudiante, LocalDate fechaQuery);

    List<Asistencia> findByFechaQueryAndIngresoConfirmado(LocalDate fechaActual, boolean ingresoConfirmado);

//    List<Asistencia> findByFechaQueryAndSalidaConfirmadoAndEstudiante_Aula_Id(LocalDate fechaActual, boolean salidaConfirmado, Long aula_id);
}
