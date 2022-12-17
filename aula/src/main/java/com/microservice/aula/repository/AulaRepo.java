package com.microservice.aula.repository;

import com.microservice.aula.models.Aula;
import com.microservice.aula.models.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AulaRepo extends JpaRepository<Aula, Long> {

    @Query("FROM Grado g WHERE g.id=?1")
    Optional<Grado> buscarGradoPorId(Long id);

    @Query("FROM Grado")
    List<Grado> listarGrados();
}
