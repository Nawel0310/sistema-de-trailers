package com.sistema.trailers.com.sistema.trailers.service;

import com.sistema.trailers.com.sistema.trailers.modelo.Genero;
import com.sistema.trailers.com.sistema.trailers.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public Genero guardarGenero(Genero genero){
        return generoRepository.save(genero);
    }

    public List<Genero> obtenerTodosLosGeneros(){
        return generoRepository.findAll();
    }

}
