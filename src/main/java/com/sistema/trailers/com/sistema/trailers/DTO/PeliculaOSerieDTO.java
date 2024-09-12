package com.sistema.trailers.com.sistema.trailers.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PeliculaOSerieDTO {

    private Integer id;
    private String titulo;
    private String sinopsis;
    private LocalDate fechaEstreno;
    private String youtubeTrailerId;
    private List<Integer> generos; // IDs de géneros
    private ImagenDTO portada;
}