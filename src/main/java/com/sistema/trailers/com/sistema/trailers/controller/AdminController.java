package com.sistema.trailers.com.sistema.trailers.controller;


import com.sistema.trailers.com.sistema.trailers.DTO.PeliculaOSerieDTO;
import com.sistema.trailers.com.sistema.trailers.modelo.PeliculaOSerie;
import com.sistema.trailers.com.sistema.trailers.service.ImagenService;
import com.sistema.trailers.com.sistema.trailers.service.PeliculaOSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/administracion")
public class AdminController {

    @Autowired
    private PeliculaOSerieService peliculaOSerieService;

    @Autowired
    private ImagenService imagenService;

    @GetMapping("/peliculas")
    public ResponseEntity<Page<PeliculaOSerieDTO>> verPagina(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {
        Page<PeliculaOSerieDTO> peliculaOSeriePage = peliculaOSerieService.obtenerPeliculasConPaginacion(pageable);
        return ResponseEntity.ok(peliculaOSeriePage);
    }

    @GetMapping("/peliculas/{id}/portada")
    public ResponseEntity<String> obtenerPortada(@PathVariable Integer id) throws IOException {
        String base64Image = imagenService.obtenerImagenBase64(id);
        return ResponseEntity.ok(base64Image);
    }

    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> guardarPeliculaOSerie(
            @RequestParam("titulo") String titulo,
            @RequestParam("sinopsis") String sinopsis,
            @RequestParam("fechaEstreno") LocalDate fechaEstreno,
            @RequestParam("youtubeTrailerId") String youtubeTrailerId,
            @RequestParam("generos") List<Integer> generos,
            @RequestParam(value = "portada", required = false) MultipartFile portada) {

        PeliculaOSerieDTO peliculaOSerieDTO = new PeliculaOSerieDTO();
        peliculaOSerieDTO.setTitulo(titulo);
        peliculaOSerieDTO.setSinopsis(sinopsis);
        peliculaOSerieDTO.setFechaEstreno(fechaEstreno);
        peliculaOSerieDTO.setYoutubeTrailerId(youtubeTrailerId);
        peliculaOSerieDTO.setGeneros(generos);

        try {
            peliculaOSerieService.guardarPeliculaOSerie(peliculaOSerieDTO, portada);
            return ResponseEntity.ok(Map.of("message", "Película/Serie guardada exitosamente."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error al guardar la Película/Serie."));
        }
    }
}

