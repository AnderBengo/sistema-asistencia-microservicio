package com.microservice.asistencia.service;

import com.microservice.asistencia.controller.dto.response.CustomResponse;
import com.microservice.asistencia.estudiantesclient.AulaClientRest;
import com.microservice.asistencia.estudiantesclient.EstudiantesClientRest;
import com.microservice.asistencia.model.Asistencia;
import com.microservice.asistencia.model.Aula;
import com.microservice.asistencia.model.Estudiante;
import com.microservice.asistencia.repository.AsistenciaRepo;
import com.microservice.asistencia.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AsistenciaServiceImpl implements AsistenciaService{

    private final AsistenciaRepo asistenciaRepo;
    private final EstudiantesClientRest estudiantesClientRest;
    private final AulaClientRest aulaClientRest;

    @Override
    public Asistencia guardarAsistencia(Asistencia asistencia) throws ServiceException {

        if(!asistencia.getIngresoConfirmado() || asistencia.getSalidaConfirmado()){
            throw new ServiceException("Parámetros inválidos");
        }

        try {
            LocalDateTime fechaActual = LocalDateTime.now();
            LocalDate fechaQuery = LocalDate.now();
            asistencia.setFechaHoraIngreso(fechaActual);
            asistencia.setFechaQuery(fechaQuery);
            asistencia.setCodigoEstudiante(asistencia.getEstudiante().getCodigoEstudiante());
            Asistencia asistenciaGuardada = asistenciaRepo.save(asistencia);
            Aula respuestaAula = aulaClientRest.buscarAula(asistencia.getEstudiante().getAulaId());
            asistenciaGuardada.setEstudiante(asistencia.getEstudiante());
            asistenciaGuardada.getEstudiante().setAula(respuestaAula);
            return asistenciaGuardada;
        } catch (DataAccessException e) {
            throw new ServiceException("Error en la base de datos al insertar la asistencia.");
        }
    }

    @Override
    public Optional<Asistencia> buscarPorId(Long id) {
        return asistenciaRepo.findById(id);
    }

    @Override
    public List<Asistencia> buscarTodo() {
        return asistenciaRepo.findAll();
    }

    @Override
    public Optional<Asistencia> buscarPorEstudianteCodigo(String codigoEstudiante) {
        LocalDate fechaActual = LocalDate.now();
        Optional<Asistencia> asistenciaEncontrada = asistenciaRepo.findByCodigoEstudianteAndFechaQuery(codigoEstudiante, fechaActual);

        if(asistenciaEncontrada.isPresent()){
            Estudiante estudiante = estudiantesClientRest.buscarEstudiantePorCodigo(codigoEstudiante);
            asistenciaEncontrada.get().setEstudiante(estudiante);
        }

        return asistenciaEncontrada;
    }

    @Override
    public Asistencia registrarSalida(Asistencia asistencia) {
        LocalDateTime fechaActual = LocalDateTime.now();
        asistencia.setFechaHoraSalida(fechaActual);
        asistencia.setCodigoEstudiante(asistencia.getEstudiante().getCodigoEstudiante());
        Asistencia asistenciaGuardada = asistenciaRepo.save(asistencia);
        Aula respuestaAula = aulaClientRest.buscarAula(asistencia.getEstudiante().getAulaId());
        asistenciaGuardada.setEstudiante(asistencia.getEstudiante());
        asistenciaGuardada.getEstudiante().setAula(respuestaAula);
        return asistenciaRepo.save(asistencia);
    }

    @Override
    public List<Asistencia> obtenerReporteAuxiliar(boolean ingreso) {
        LocalDate fechaActual = LocalDate.now();
        List<Estudiante> estudiantes = estudiantesClientRest.buscarEstudiantesPorAulaId(1L);
        List<Asistencia> asistenciasDeHoy = asistenciaRepo.findByFechaQueryAndIngresoConfirmado(fechaActual, ingreso);

        List<Asistencia> asistenciasFiltradas = new ArrayList<>();
        for (Estudiante estudiante: estudiantes) {
            for (Asistencia asistencia: asistenciasDeHoy) {
                if(asistencia.getCodigoEstudiante().equals(estudiante.getCodigoEstudiante())){
                    asistenciasFiltradas.add(asistencia);
                    break;
                }
            }
        }


        return asistenciasFiltradas;
    }


}
