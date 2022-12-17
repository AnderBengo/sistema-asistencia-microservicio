package com.microservice.estudiantes.services;

import com.microservice.estudiantes.models.*;
import com.microservice.estudiantes.repository.AsistenciaSimulacionRepo;
import com.microservice.estudiantes.repository.SimulacionAulaRepo;
import com.microservice.estudiantes.restclient.AulaRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SimulacionAulaServiceImpl implements SimulacionAulaService{

    private final AulaRestClient aulaRestClient;
    private final SimulacionAulaRepo simulacionAulaRepo;
    private final AsistenciaSimulacionRepo asistenciaSimulacionRepo;

    @Override
    public SimulacionAula crearSimulacion(SimulacionAula simulacionAula) {

        Grado grado = aulaRestClient.buscarGradoPorId(simulacionAula.getGradoId());

        List<Estudiante> estudiantes = grado.getAulas()
                .stream()
                .flatMap(aula -> aula.getEstudiantes().stream())
                .collect(Collectors.toList());

        List<SimulacionAulaItem> listaItemsAulaSimulacion = generarItemsAulaSimulacionConEstudiante(simulacionAula.getCantidadSimulacion(), estudiantes);
        simulacionAula.setItemsSimulacion(listaItemsAulaSimulacion);

        simulacionAula.setActivo(true);



        return simulacionAulaRepo.save(simulacionAula);
    }

    private List<SimulacionAulaItem> generarItemsAulaSimulacionConEstudiante(Integer cantidadSimulacion, List<Estudiante> estudiantes) {
        Collections.shuffle(estudiantes);

        final int chunkSize = cantidadSimulacion;
        final AtomicInteger counter = new AtomicInteger();

        final Collection<List<Estudiante>> result = estudiantes.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
                .values();

        return result.stream().map(es -> {
            SimulacionAulaItem simulacionAulaItem = new SimulacionAulaItem();
            simulacionAulaItem.setAsistenciasSimulacion(generarAsistenciasSimulacion(es));
            return simulacionAulaItem;
        }).collect(Collectors.toList());
    }

    private List<AsistenciaSimulacion> generarAsistenciasSimulacion(List<Estudiante> estudiantes) {

        return estudiantes.stream().map(e -> {
            AsistenciaSimulacion asistenciaSimulacion = new AsistenciaSimulacion();
            asistenciaSimulacion.setEstudiante(e);
            asistenciaSimulacion.setIngresoConfirmado(false);
            asistenciaSimulacion.setSalidaConfirmado(false);
            return asistenciaSimulacion;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<SimulacionAula> buscarSimulacionAulaPorId(Long id) {
        return simulacionAulaRepo.findById(id);
    }

    @Override
    public List<SimulacionAula> listarSimulacionAulaPorGrado(Long gradoId) {
        LocalDateTime fechaAhora = LocalDateTime.now();
        List<SimulacionAula> simulacionAulas = simulacionAulaRepo.listarSimulacionAulaPorGrado(gradoId);

        List<SimulacionAula> simulacionesAula = simulacionAulas.stream().map(s -> {
            if (fechaAhora.isAfter(s.getFechaFin())) {
                s.setActivo(false);
            }
            return s;
        }).collect(Collectors.toList());

        return simulacionesAula;
    }

    @Override
    public AsistenciaSimulacion registrarAsistenciaSimulacion(AsistenciaSimulacion asistenciaSimulacion) {
        List<AsistenciaSimulacion> listaAsistencias = asistenciaSimulacionRepo.buscarPorCodigoEstudiante(asistenciaSimulacion.getEstudiante().getCodigoEstudiante());

        AsistenciaSimulacion ultimaAsistencia = listaAsistencias.get(listaAsistencias.size() - 1);

        if(ultimaAsistencia.getIngresoConfirmado()) {
           return null;
        }

        ultimaAsistencia.setIngresoConfirmado(true);

        return asistenciaSimulacionRepo.save(ultimaAsistencia);
    }

    @Override
    public AsistenciaSimulacion registrarSalidaSimulacion(AsistenciaSimulacion asistenciaSimulacion) {
        List<AsistenciaSimulacion> listaAsistencias = asistenciaSimulacionRepo.buscarPorCodigoEstudiante(asistenciaSimulacion.getEstudiante().getCodigoEstudiante());

        AsistenciaSimulacion ultimaAsistencia = listaAsistencias.get(listaAsistencias.size() - 1);

        if(!ultimaAsistencia.getIngresoConfirmado()) {
            return null;
        }

        ultimaAsistencia.setSalidaConfirmado(true);

        return asistenciaSimulacionRepo.save(ultimaAsistencia);
    }


}
