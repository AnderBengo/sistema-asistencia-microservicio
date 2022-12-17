package com.microservice.aula.service;

import com.microservice.aula.estudiantesclient.EstudiantesClientRest;
import com.microservice.aula.models.Aula;
import com.microservice.aula.models.Auxiliar;
import com.microservice.aula.models.Estudiante;
import com.microservice.aula.repository.AuxiliarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuxiliarServiceImpl implements AuxiliarService{

    private final AuxiliarRepo auxiliarRepo;
    private final EstudiantesClientRest estudiantesClientRest;

    @Override
    public Optional<Auxiliar> buscarPorUsuarioAndPassword(String usuario, String password) {
        return auxiliarRepo.findByUsuarioAndPassword(usuario, password);
    }

    @Override
    public Optional<Auxiliar> buscarPorAuxiliarId(Long id) {
        Optional<Auxiliar> auxiliar = auxiliarRepo.findById(id);

        if(auxiliar.isPresent()) {

            List<Aula> aulas = auxiliar.get().getAulas().stream().map(a -> {
                List<Estudiante> estudiantes = estudiantesClientRest.buscarEstudiantesPorAulaId(a.getId());
                a.setEstudiantes(estudiantes);
                return a;
            }).collect(Collectors.toList());
            auxiliar.get().setAulas(aulas);
        }

        return auxiliar;
    }
}
