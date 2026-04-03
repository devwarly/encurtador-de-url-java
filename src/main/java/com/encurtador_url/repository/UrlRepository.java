package com.encurtador_url.repository;

import com.encurtador_url.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByUrlCurta(String url);

    /*
    * Oque essa interface ta fazendo no meu código?
    *
    * Quando declaro o 'Optional<Url> findByUrlCurta(String url);',
    * eu estou dizendo:
    *
    * - find = quero um select
    * - by = aqui começa o filtro where
    * - UrlCurta = quero que filtre pela coluna url curta
    *
    * */
}
