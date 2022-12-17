package com.microservice.aula.repository;

import com.microservice.aula.models.Auxiliar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuxiliarRepo extends JpaRepository<Auxiliar, Long> {

    Optional<Auxiliar> findByUsuarioAndPassword(String usuario, String password);
}
