package com.encurtador_url.service;

import com.encurtador_url.model.Url;
import com.encurtador_url.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlService {

    /* Isso evita que o banco receba um erro 'NullPointerException',
    *   pois o Spring garante que o repositório estará lá assim
    *   que a classe for criada
    */


    private final UrlRepository urlRepository;

    public String sortearCaracteres(int tamanho){
        String caracteresPermitidos = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

        Random random = new Random();
        StringBuilder urlCurta = new StringBuilder();

        for (int i = 0; i < tamanho; i++){
            int posicaoVez = random.nextInt(caracteresPermitidos.length());


            char caracter = caracteresPermitidos.charAt(posicaoVez);

            urlCurta.append(caracter);

        }

        return urlCurta.toString();
    }

    public Url encurtarUrl(String urlOriginalDestino){

        String codigoGerado;

        do{
            codigoGerado = sortearCaracteres(6);
        }while (urlRepository.findByUrlCurta(codigoGerado).isPresent());
        // Enquando o resultado gerado estiver presete o código repete o sorteio

        Url url = new Url();

        url.setCriadoEm(LocalDateTime.now());
        url.setUrlCurta(codigoGerado);
        url.setUrlOriginal(urlOriginalDestino);

        urlRepository.save(url);
        return url;
    }

    public String obterUrlOriginal(String codigo){
        Url urlEncontrada = urlRepository.findByUrlCurta(codigo).orElseThrow(()-> new RuntimeException("URL não encontrada para o código:" + codigo));

       LocalDateTime expiraEm = LocalDateTime.now();

       LocalDateTime dataBanco = urlEncontrada.getExpiraEm();

       if (dataBanco != null && dataBanco.isBefore(expiraEm)){
           urlRepository.delete(urlEncontrada);

           throw new RuntimeException("Este link expirou!");
       }

       return urlEncontrada.getUrlOriginal();
    }

}
