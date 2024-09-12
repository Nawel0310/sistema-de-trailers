package com.sistema.trailers.com.sistema.trailers.modelo;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_imagen")
    private Integer id;

    @Lob //Se usa para almacenar datos binarios grandes como imagenes.
    @Column(name = "datos_imagen", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] datos;

    @Column(name="tipo_imagen",nullable = false)
    private String tipo;

    @OneToOne(mappedBy = "portada")
    private PeliculaOSerie peliculaOSerie;
}
