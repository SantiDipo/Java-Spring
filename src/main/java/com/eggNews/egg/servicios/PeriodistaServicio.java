/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.servicios;

import com.eggNews.egg.entidades.Periodista;
import com.eggNews.egg.entidades.Usuario;
import com.eggNews.egg.enumeraciones.Rol;
import com.eggNews.egg.repositorios.PeriodistaRepositorio;
import com.eggNews.egg.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santiago D'Ippolito
 */
@Service
public class PeriodistaServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private PeriodistaRepositorio periodistaRepositorio;
    
    @Transactional
    public void ingresarSueldo(String id, Integer sueldoMensual) {

        Optional<Periodista> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Periodista periodista = respuesta.get();
            periodista.setSueldoMensual(sueldoMensual);
            periodistaRepositorio.save(periodista);
        }
    }

//    @Transactional
//    public void guardar(){
//      Periodista periodista= (Periodista) periodistaRepositorio.buscarPorNombre("test");
//        System.out.printf(periodista.getNombreUsuario());
//    }

}
