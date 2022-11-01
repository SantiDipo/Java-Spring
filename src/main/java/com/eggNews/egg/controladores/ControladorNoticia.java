/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.controladores;

import com.eggNews.egg.exepciones.MiException;
import com.eggNews.egg.servicios.ServicioNoticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Santiago D'Ippolito
 */
@Controller
@RequestMapping("/")
public class ControladorNoticia {

    @Autowired
    protected ServicioNoticia servicioNoticia;

    @GetMapping("/inicio")
    public String index() {
        return "index.html";
    }

    @GetMapping("/crear")
    public String crear() {
        return "crearNoticia.html";
    }

    @GetMapping("/modificar")
    public String modificar() {
        return "modificarNoticia.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String titulo, String cuerpo) throws MiException {
        servicioNoticia.crearNoticia(Long.MIN_VALUE, titulo, cuerpo);
        return "crearNoticia.html";
    }

    @PostMapping("/modificarNoticia")
    public String modificarN(@RequestParam String titulo, String cuerpo) throws MiException {
        servicioNoticia.modificarNoticia(Long.MIN_VALUE, titulo, cuerpo);
        return "modificarNoticia.html";
    }

    @PostMapping("/listar")
    public String listar() {
        servicioNoticia.listarNoticia();
        return "modificarNoticia.html";
    }
}
