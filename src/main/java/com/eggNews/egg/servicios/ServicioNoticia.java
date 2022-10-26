/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.servicios;

import com.eggNews.egg.entidades.Noticia;
import com.eggNews.egg.exepciones.MiException;
import com.eggNews.egg.repositorios.NoticiaRepositorio;
import javafx.scene.image.Image;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santiago D'Ippolito
 */
@Service
public class ServicioNoticia {

    @Autowired
    NoticiaRepositorio noticiarepositorio;

    @Transactional
    public void crearNoticia(Long id, String titulo, String cuerpo, Image foto) throws MiException {

        validar(id, titulo, cuerpo, foto);
        
        Noticia noticia = new Noticia();
        
        noticia.setId(id);
        noticia.setCuerpo(cuerpo);
        noticia.setTitulo(titulo);
        noticia.setFoto(foto);

        noticiarepositorio.save(noticia);
    }
    
    

    public void validar(Long id, String titulo, String cuerpo, Image foto) throws MiException {
        if (id == null) {
            throw new MiException("El id no debe ser nulo");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new MiException("El titulo no debe ser nulo o estar vacio");
        }
        if (cuerpo == null || cuerpo.isEmpty()) {
            throw new MiException("El cuerpo no debe ser nulo o estar vacio");
        }
        if (foto.isError() || foto == null) {
            throw new MiException("Su imagen esta nula o no es compatible o posee un error");
        }
    }
}
