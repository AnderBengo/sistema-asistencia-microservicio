package com.microservice.aula.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Auxiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String usuario;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "auxiliar")
    @JsonIgnoreProperties({"auxiliar","hibernateLazyInitializer", "handler"})
    private List<Aula> aulas;
}
