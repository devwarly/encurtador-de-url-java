package com.encurtador_url.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UrlDTO {
    private String urlOriginal;
    private String urlCurta;

    public UrlDTO(String urlOriginal, String urlCurta) {
        this.urlOriginal = urlOriginal;
        this.urlCurta = urlCurta;
    }
}
