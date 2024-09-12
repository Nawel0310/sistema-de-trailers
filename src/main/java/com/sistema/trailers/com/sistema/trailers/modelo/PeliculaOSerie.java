package com.sistema.trailers.com.sistema.trailers.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaOSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pelicula_o_serie") //Importante agregarle un nombre
    // a la columna donde guardo mi entidad principal
    private Integer id;

    @NotBlank
    private String titulo;

    @NotBlank
    @Column(name = "sinopsis", columnDefinition = "LONGTEXT", nullable = false)
    private String sinopsis;

    @NotNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate fechaEstreno;

    @NotBlank
    private String youtubeTrailerId;

    @NotEmpty//Esta colección de elementos NO puede estar vacía
    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REMOVE})//Una película/serie puede tener muchos géneros,
    //y muchos generos pueden pertenecer muchas películas/series
    @JoinTable(name="genero_pelicula_o_serie",//Nombre de la tabla INTERMEDIA
            joinColumns = @JoinColumn(name="id_pelicula_o_serie"),// clave foranea de pelicula/serie
            inverseJoinColumns = @JoinColumn(name="id_genero")) //clave foranea de genero
    private List<Genero>generos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_imagen_portada", referencedColumnName = "id_imagen")
    private Imagen portada;

}
