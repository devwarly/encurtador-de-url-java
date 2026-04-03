package com.encurtador_url.controller;

import com.encurtador_url.dto.UrlRequest;
import com.encurtador_url.model.Url;
import com.encurtador_url.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<Url> setUrl(@RequestBody UrlRequest request){
        urlService.encurtarUrl(request.getUrlOriginal());

        Url urlSalva = urlService.encurtarUrl(request.getUrlOriginal());

        return ResponseEntity.status(201).body(urlSalva);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Url> getUrl(@PathVariable String codigo){
        String urlReal = urlService.obterUrlOriginal(codigo);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Location", urlReal);
        headers.add("Cache-Control", "no-cache");

        return new  ResponseEntity<>(headers, HttpStatus.FOUND);
    }



}
