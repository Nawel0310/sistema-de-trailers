package com.sistema.trailers.com.sistema.trailers.service;

import com.sistema.trailers.com.sistema.trailers.excepciones.FileNotFoundException;
import com.sistema.trailers.com.sistema.trailers.excepciones.ImagenNoGuardadaException;
import com.sistema.trailers.com.sistema.trailers.modelo.Imagen;
import com.sistema.trailers.com.sistema.trailers.repositories.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ImagenService {

    private final ImagenRepository imagenRepository;

    @Autowired
    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    public Imagen guardarImagen (MultipartFile file) throws IOException {
        try {
            Imagen imagen =
                    Imagen.builder().
                            datos(file.getBytes()).
                            tipo(file.getContentType()).
                            build();
            return imagenRepository.save(imagen);
        } catch (IOException e) {
            //Este catch es para todas aquellas excepciones relacionadas al INPUT o OUTPUT
            throw new ImagenNoGuardadaException("Error al guardar la imagen en la BD.", e);
        } catch (Exception e) {
            // Este catch maneja cualquier otro tipo de excepción que pueda ocurrir al guardar
            throw new ImagenNoGuardadaException("Ocurrió un problema al guardar la imagen.", e);
        }
    }

    public String obtenerImagenBase64(Integer id) throws IOException {
        Imagen imagen = imagenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
        String base64Image = "data:" + imagen.getTipo() + ";base64," +
                Base64.getEncoder().encodeToString(imagen.getDatos());
        return base64Image;
    }



    public Imagen obtenerImagenPorId(Integer id) {
        return imagenRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("Imagen no encontrada con id: " + id));
    }

    public List<Imagen> obtenerTodasLasImagenes() {
        return imagenRepository.findAll();
    }

    public void eliminarImagen(Integer id) {
        imagenRepository.deleteById(id);
    }

}
