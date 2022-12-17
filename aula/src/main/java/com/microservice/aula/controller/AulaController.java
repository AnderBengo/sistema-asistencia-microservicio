package com.microservice.aula.controller;

import com.microservice.aula.controller.dto.response.CustomResponse;
import com.microservice.aula.models.Aula;
import com.microservice.aula.models.Grado;
import com.microservice.aula.service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.microservice.aula.controller.dto.response.CodeEnum.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/aulas")
public class AulaController {

    private final AulaService aulaService;


    @GetMapping("/obtener-aulas")
    public ResponseEntity<?> obtenerAulas() {
        return ResponseEntity
                .status(OK)
                .body(CustomResponse.builder()
                        .code(SUCCESS)
                        .message("Transacción exitosa!")
                        .data(aulaService.listarAulas()).build());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarAula(@PathVariable Long id) {
        Optional<Aula> aula = aulaService.buscarPorAulaId(id);

        if(!aula.isPresent()) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(CustomResponse.builder()
                            .message("No se encontró un aula con ese ID: "+id)
                            .code(WARNING).build());

        }


        return ResponseEntity
                .status(OK)
                .body(CustomResponse.builder()
                        .code(SUCCESS)
                        .message("Transacción exitosa!")
                        .data(aula.get()).build());
    }

    @GetMapping("/buscar-aula-id/{id}")
    public Aula buscarAulaPorId(@PathVariable Long id) {
        Optional<Aula> aula = aulaService.buscarPorAulaId(id);

        if(!aula.isPresent()) {
            return null;
        }


        return aula.get();
    }

    @GetMapping("/grado/{gradoId}")
    public Grado buscarGradoPorId(@PathVariable Long gradoId) {
        Optional<Grado> grado = aulaService.buscarGradoPorId(gradoId);

        if(!grado.isPresent()) {
            return null;
        }


        return grado.get();
    }

    @GetMapping("/grados")
    public List<Grado> listarGrados() {
        return aulaService.listarGrados();
    }
}
