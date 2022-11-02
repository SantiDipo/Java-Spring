/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.controladores;

import com.eggNews.egg.entidades.Noticia;
import com.eggNews.egg.exepciones.MiException;
import com.eggNews.egg.servicios.ServicioNoticia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/listar")
    public String listar(ModelMap modelo) {
       List<Noticia> noticias = servicioNoticia.listarNoticia();
       modelo.addAttribute("noticias", noticias);
        return "listarNoticia.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long id, String titulo, String cuerpo) throws MiException {
        servicioNoticia.crearNoticia(Long.MIN_VALUE, titulo, cuerpo);
        return "crearNoticia.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificarenlista(@PathVariable Long id, String titulo, String cuerpo, ModelMap modelo) throws MiException, MiException {
        modelo.put("noticia", servicioNoticia.getOne(id));
        return "modificarNoticia.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificarN(@PathVariable Long id, String titulo, String cuerpo, ModelMap modelo){
        try {
            servicioNoticia.modificarNoticia(id, titulo, cuerpo);
            return "redirect:../listar";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "modificarNoticia.html";
        }
        
     }   
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo){
            servicioNoticia.darDeBajaNoticia(id);
            return "redirect:../listar";
     }   
    }

 
