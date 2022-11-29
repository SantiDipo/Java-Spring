/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.servicios;

import com.eggNews.egg.entidades.Imagen;
import com.eggNews.egg.entidades.Noticia;
import com.eggNews.egg.entidades.Periodista;
import com.eggNews.egg.exepciones.MiException;
import com.eggNews.egg.repositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Santiago D'Ippolito
 */
@Service
public class ServicioNoticia {

    @Autowired
    private NoticiaRepositorio noticiarepositorio;
    @Autowired
    private ImagenServicio imagenservicio;

    @Transactional
    public void crearNoticia(MultipartFile archivo, Long id, String titulo, String cuerpo) throws MiException {

        validar(id, titulo, cuerpo);
        Noticia noticia = new Noticia();

        noticia.setId(id);
        noticia.setCuerpo(cuerpo);
        noticia.setTitulo(titulo);
        Imagen imagen = imagenservicio.guardarImagen(archivo);
        noticia.setImagen(imagen);
        noticiarepositorio.save(noticia);
    }

    public List<Noticia> listarNoticia() {
        List<Noticia> noticia = new ArrayList();
        noticia = noticiarepositorio.findAll();
        return noticia;
    }

    @Transactional
    public void modificarNoticia(MultipartFile archivo, Long id, String titulo, String cuerpo) throws MiException {

        validar(id, titulo, cuerpo);
        Optional<Noticia> respuesta = noticiarepositorio.findById(id);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            noticia.setId(id);
            noticia.setCuerpo(cuerpo);
            noticia.setTitulo(titulo);
            String idImagen = null;
            if (noticia.getImagen() != null) {
                idImagen = noticia.getImagen().getId();
            }
            Imagen imagen = imagenservicio.actualizarImagen(archivo, idImagen);
            noticia.setImagen(imagen);
            noticiarepositorio.save(noticia);
        }
    }

    @Transactional
    public void darDeBajaNoticia(Long id) {
        Noticia noticia = noticiarepositorio.getById(id);
        noticiarepositorio.delete(noticia);
    }

    public Noticia getOne(Long id) {
        return noticiarepositorio.getOne(id);
    }

    public void validar(Long id, String titulo, String cuerpo) throws MiException {
//        if (id == null) {
//            throw new MiException("El id no debe ser nulo");
//        }
        if (titulo == null || titulo.isEmpty()) {
            throw new MiException("El titulo no debe ser nulo o estar vacio");
        }
        if (cuerpo == null || cuerpo.isEmpty()) {
            throw new MiException("El cuerpo no debe ser nulo o estar vacio");
        }
    }

    public Optional<Noticia> listarNoticiaPorId(Long id) {
        Optional<Noticia> noticia = noticiarepositorio.findById(id);
        return noticia;
    }

}
