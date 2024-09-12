package com.sistema.trailers.com.sistema.trailers.repositories;

import com.sistema.trailers.com.sistema.trailers.modelo.PeliculaOSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaOSerieRepository extends JpaRepository<PeliculaOSerie,Integer> {
}
