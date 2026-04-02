package com.encurtador_url.model;

import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

// Anotação do Lombook que gera automaticamente os Getters, Setters, Costrutores etc
@Data
public class Url {

    // A anotação '@Id' indica que esse atibuto é a chave primaria da tabela
    @Id
    private long id;

    private long urOriginal;
    private String urlCurta;
    private LocalDateTime criadoEm;

}
