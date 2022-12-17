package com.microservice.asistencia.service;

import com.microservice.asistencia.model.Asistencia;
import com.microservice.asistencia.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface AsistenciaService {

    Asistencia guardarAsistencia(Asistencia asistencia) throws ServiceException;

    Optional<Asistencia> buscarPorId(Long id);

    List<Asistencia> buscarTodo();

    Optional<Asistencia> buscarPorEstudianteCodigo(String codigoEstudiante);

    Asistencia registrarSalida(Asistencia asistencia);

    List<Asistencia> obtenerReporteAuxiliar(boolean ingreso);
}
