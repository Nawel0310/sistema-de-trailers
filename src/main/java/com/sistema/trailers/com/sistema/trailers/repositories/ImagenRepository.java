package com.sistema.trailers.com.sistema.trailers.repositories;

import com.sistema.trailers.com.sistema.trailers.modelo.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen,Integer> {
}
