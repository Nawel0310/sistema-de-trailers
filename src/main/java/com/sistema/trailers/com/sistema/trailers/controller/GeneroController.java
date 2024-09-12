package com.sistema.trailers.com.sistema.trailers.controller;

import com.sistema.trailers.com.sistema.trailers.modelo.Genero;
import com.sistema.trailers.com.sistema.trailers.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping("/guardarGenero")
    public ResponseEntity<Genero> crearGenero(@RequestBody Genero genero){
        //RequestBody ayuda a que Spring entienda donde debe buscar los
        //datos de la solicitud, siendo en este caso en el objeto
        //"genero" de la solicitud
        Genero nuevoGenero= generoService.guardarGenero(genero);
        return new ResponseEntity<>(nuevoGenero, HttpStatus.CREATED);
    }

    @GetMapping("/obtenerGeneros")
    public ResponseEntity<List<Genero>> obtenerGeneros(){
        List<Genero> generos = generoService.obtenerTodosLosGeneros();
        return new ResponseEntity<>(generos,HttpStatus.OK);
    }

}
