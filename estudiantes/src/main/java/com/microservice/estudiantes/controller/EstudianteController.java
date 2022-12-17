package com.microservice.estudiantes.controller;

import com.microservice.estudiantes.models.AsistenciaSimulacion;
import com.microservice.estudiantes.models.Estudiante;
import com.microservice.estudiantes.models.SimulacionAula;
import com.microservice.estudiantes.repository.AsistenciaSimulacionRepo;
import com.microservice.estudiantes.repository.SimulacionAulaRepo;
import com.microservice.estudiantes.services.EstudianteService;
import com.microservice.estudiantes.controller.dto.response.CustomResponse;
import com.microservice.estudiantes.services.SimulacionAulaService;
import com.microservices.estudiantes.services.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.microservice.estudiantes.controller.dto.response.CodeEnum.SUCCESS;
import static com.microservice.estudiantes.controller.dto.response.CodeEnum.WARNING;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;
    private final SimulacionAulaService simulacionAulaService;
    private final AsistenciaSimulacionRepo asistenciaSimulacionRepo;

    @GetMapping("/por-aula/{codigoAula}")
    public List<Estudiante> buscarEstudiantesPorCodigoAula(@PathVariable Long codigoAula) throws ServiceException {
        return estudianteService.buscarEstudiantesPorAulaId(codigoAula);
    }

    @GetMapping("/por-codigo-estudiante/{codigoEstudiante}")
    public Estudiante buscarEstudiantesPorCodigoAula(@PathVariable String codigoEstudiante) {
        Optional<Estudiante> estudianteEncontrado = estudianteService.buscarEstudiantePorCodigo(codigoEstudiante);

        if(estudianteEncontrado.isEmpty()) return null;
        return estudianteEncontrado.get();
    }

    @PostMapping("/crear-simulacion-aula")
    public SimulacionAula crearSimulacion(@RequestBody SimulacionAula simulacionAula){
        if(simulacionAula == null) {
            return null;
        }
        return simulacionAulaService.crearSimulacion(simulacionAula);
    }

    @GetMapping("/simulacion-aula-por-id/{simulacionAulaId}")
    public SimulacionAula buscarSimulacionAulaPorId(@PathVariable Long simulacionAulaId) {

        Optional<SimulacionAula> simulacionAula = simulacionAulaService.buscarSimulacionAulaPorId(simulacionAulaId);

        if(simulacionAula.isEmpty()) {
            return null;
        }

        return simulacionAula.get();
    }

    @GetMapping("/listar-aulas-simulacion/{gradoId}")
    public List<SimulacionAula> listarSimulacionAulaPorGrado(@PathVariable Long gradoId) {
        return simulacionAulaService.listarSimulacionAulaPorGrado(gradoId);
    }

    @PutMapping("/registrar-asistencia")
    public ResponseEntity<?> registrarAsistencia(@RequestBody AsistenciaSimulacion asistencia) throws ServiceException {



        Optional<Estudiante> estudiante = estudianteService.buscarEstudiantePorCodigo(asistencia.getEstudiante().getCodigoEstudiante());

        if(estudiante.isEmpty()){
            return ResponseEntity.status(NOT_FOUND)
                    .body(CustomResponse.builder()
                            .code(WARNING).message("No existe estudiante con el código: "+ asistencia.getEstudiante().getCodigoEstudiante()).build());
        }

        List<AsistenciaSimulacion> listaAsistencias = asistenciaSimulacionRepo.buscarPorCodigoEstudiante(asistencia.getEstudiante().getCodigoEstudiante());

        if(listaAsistencias.size() == 0) {
            asistencia.setEstudiante(estudiante.get());
            return ResponseEntity.status(BAD_REQUEST)
                    .body(CustomResponse.builder()
                            .code(WARNING).message("Estudiante no pertenece a esta aula simulación ")
                            .data(asistencia).build());
        }

        AsistenciaSimulacion asistenciaEncontrada = simulacionAulaService.registrarAsistenciaSimulacion(asistencia);

        if(asistenciaEncontrada == null) {
            AsistenciaSimulacion asistenciaSimulacion = new AsistenciaSimulacion();
            asistenciaSimulacion.setEstudiante(estudiante.get());
            return ResponseEntity
                    .status(BAD_REQUEST)
                    .body(CustomResponse.builder()
                            .code(WARNING).message("No se puede registrar la asistencia del estudiante 2 veces.")
                            .data(asistenciaSimulacion)
                            .build());
        }

        asistenciaEncontrada.setEstudiante(estudiante.get());


            return ResponseEntity
                    .status(CREATED)
                    .body(CustomResponse.builder()
                            .code(SUCCESS).message("Asistencia registrada")
                            .data(asistenciaEncontrada).build());

    }

    @PutMapping("/registrar-salida")
    public ResponseEntity<?> registrarSalida(@RequestBody AsistenciaSimulacion asistencia) throws ServiceException {



        Optional<Estudiante> estudiante = estudianteService.buscarEstudiantePorCodigo(asistencia.getEstudiante().getCodigoEstudiante());

        if(estudiante.isEmpty()){
            return ResponseEntity.status(NOT_FOUND)
                    .body(CustomResponse.builder()
                            .code(WARNING).message("No existe estudiante con el código: "+ asistencia.getEstudiante().getCodigoEstudiante()).build());
        }

        List<AsistenciaSimulacion> listaAsistencias = asistenciaSimulacionRepo.buscarPorCodigoEstudiante(asistencia.getEstudiante().getCodigoEstudiante());

        if(listaAsistencias.size() == 0) {
            asistencia.setEstudiante(estudiante.get());
            return ResponseEntity.status(BAD_REQUEST)
                    .body(CustomResponse.builder()
                            .code(WARNING).message("Estudiante no pertenece a esta aula simulación ")
                            .data(asistencia).build());
        }

        AsistenciaSimulacion asistenciaEncontrada = simulacionAulaService.registrarSalidaSimulacion(asistencia);

        if(asistenciaEncontrada == null) {
            AsistenciaSimulacion asistenciaSimulacion = new AsistenciaSimulacion();
            asistenciaSimulacion.setEstudiante(estudiante.get());
            return ResponseEntity
                    .status(BAD_REQUEST)
                    .body(CustomResponse.builder()
                            .code(WARNING).message("No hay asistencia de ingreso registrada. No se puede registrar salida.")
                            .data(asistenciaSimulacion)
                            .build());
        }

        asistenciaEncontrada.setEstudiante(estudiante.get());


        return ResponseEntity
                .status(CREATED)
                .body(CustomResponse.builder()
                        .code(SUCCESS).message("Salida registrada")
                        .data(asistenciaEncontrada).build());

    }
}
