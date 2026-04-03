package com.encurtador_url.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

// Anotação do Lombook que gera automaticamente os Getters, Setters, Costrutores etc
@Data
@Entity
@Table(name = "urls")
public class Url {

    // A anotação '@Id' indica que esse atibuto é a chave primaria da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlOriginal;
    private String urlCurta;
    private LocalDateTime criadoEm;
    private LocalDateTime expiraEm;

}
