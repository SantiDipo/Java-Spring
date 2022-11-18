/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.servicios;

import com.eggNews.egg.entidades.Imagen;
import com.eggNews.egg.exepciones.MiException;
import com.eggNews.egg.repositorios.ImagenRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Santiago D'Ippolito
 */
@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenrepositorio;

    public Imagen guardarImagen(MultipartFile archivo) throws MiException {

        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                return imagenrepositorio.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public Imagen actualizarImagen(MultipartFile archivo, String idImagen) throws MiException {

        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                if (idImagen != null) {
                    Optional<Imagen> respuesta = imagenrepositorio.findById(idImagen);
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());
                imagenrepositorio.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
