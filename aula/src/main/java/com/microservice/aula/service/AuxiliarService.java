package com.microservice.aula.service;

import com.microservice.aula.models.Auxiliar;

import java.util.Optional;

public interface AuxiliarService {

    Optional<Auxiliar> buscarPorUsuarioAndPassword(String usuario, String password);
    Optional<Auxiliar> buscarPorAuxiliarId(Long id);
}
