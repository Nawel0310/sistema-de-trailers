package com.sistema.trailers.com.sistema.trailers.service;


import com.sistema.trailers.com.sistema.trailers.DTO.ImagenDTO;
import com.sistema.trailers.com.sistema.trailers.DTO.PeliculaOSerieDTO;
import com.sistema.trailers.com.sistema.trailers.modelo.Genero;
import com.sistema.trailers.com.sistema.trailers.modelo.Imagen;
import com.sistema.trailers.com.sistema.trailers.modelo.PeliculaOSerie;
import com.sistema.trailers.com.sistema.trailers.repositories.GeneroRepository;
import com.sistema.trailers.com.sistema.trailers.repositories.PeliculaOSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaOSerieService {

    @Autowired
    private PeliculaOSerieRepository peliculaOSerieRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ImagenService imagenService;

    public void guardarPeliculaOSerie(PeliculaOSerieDTO dto, MultipartFile portada) {
        // Crear y configurar la entidad PeliculaOSerie
        PeliculaOSerie pelicula = new PeliculaOSerie();
        pelicula.setTitulo(dto.getTitulo());
        pelicula.setSinopsis(dto.getSinopsis());
        pelicula.setFechaEstreno(dto.getFechaEstreno());
        pelicula.setYoutubeTrailerId(dto.getYoutubeTrailerId());

        // Obtener los géneros seleccionados
        List<Genero> generos = generoRepository.findAllById(dto.getGeneros());
        pelicula.setGeneros(generos);

        // Manejo de la portada
        if (portada != null && !portada.isEmpty()) {
            try {
                Imagen imagen = imagenService.guardarImagen(portada);
                pelicula.setPortada(imagen);
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar la portada.", e);
            }
        }

        // Guardar Pelicula/Serie en la BD
        peliculaOSerieRepository.save(pelicula);
    }

    public Page<PeliculaOSerieDTO> obtenerPeliculasConPaginacion(Pageable pageable) {
        Page<PeliculaOSerie> peliculaOSeriePage = peliculaOSerieRepository.findAll(pageable);
        // Convertir la entidad a DTO
        return peliculaOSeriePage.map(pelicula -> {
            PeliculaOSerieDTO dto = new PeliculaOSerieDTO();
            dto.setId(pelicula.getId());
            dto.setTitulo(pelicula.getTitulo());
            dto.setSinopsis(pelicula.getSinopsis());
            dto.setFechaEstreno(pelicula.getFechaEstreno());
            dto.setYoutubeTrailerId(pelicula.getYoutubeTrailerId());
            dto.setGeneros(pelicula.getGeneros().stream().map(Genero::getId).collect(Collectors.toList()));
            if (pelicula.getPortada() != null) {
                ImagenDTO imagenDTO = new ImagenDTO();
                imagenDTO.setId(pelicula.getPortada().getId());
                imagenDTO.setDatos(pelicula.getPortada().getDatos());
                imagenDTO.setTipo(pelicula.getPortada().getTipo());
                dto.setPortada(imagenDTO);
            }
            return dto;
        });
    }

    }



