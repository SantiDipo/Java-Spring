/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.controladores;

import com.eggNews.egg.entidades.Imagen;
import com.eggNews.egg.entidades.Noticia;
import com.eggNews.egg.entidades.Periodista;
import com.eggNews.egg.entidades.Usuario;
import com.eggNews.egg.exepciones.MiException;
import com.eggNews.egg.servicios.ServicioNoticia;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Santiago D'Ippolito
 */
@Controller
@RequestMapping("/noticia")
public class ControladorNoticia {
   
    @Autowired
    protected ServicioNoticia servicioNoticia;
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO', 'ROLE_PERIODISTA', 'ROLE_ADMINISTRADOR')")
    @GetMapping("/inicio")
    public String listarIndex(ModelMap modelo, HttpSession session) {
        
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        if(logueado.getRol().toString().equals("ADMINISTRADOR")){
            return "redirect:/admin/dashboard";
        }
         if(logueado.getRol().toString().equals("PERIODISTA")){
            return "redirect:/periodista/dashboard";
         }
        
        List<Noticia> noticias = servicioNoticia.listarNoticia();
        modelo.addAttribute("noticias", noticias);
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

    @GetMapping("/verNoticia")
    public String listar(ModelMap modelo, Long id) {
        Optional<Noticia> noticia = servicioNoticia.listarNoticiaPorId(id);
        modelo.addAttribute("noticia", noticia);
        return "listarNoticia.html";
    }

    @PostMapping("/registro")
    public String registro( MultipartFile archivo,@RequestParam(required = false) Long id, String titulo, String cuerpo,Periodista periodista) throws MiException {
        servicioNoticia.crearNoticia(archivo, id, titulo, cuerpo, periodista);
        return "crearNoticia.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificarenlista(@PathVariable Long id, String titulo, String cuerpo, ModelMap modelo) throws MiException, MiException {
        modelo.put("noticia", servicioNoticia.getOne(id));
        return "modificarNoticia.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificarN(@PathVariable Long id, String titulo, String cuerpo, ModelMap modelo,MultipartFile archivo) {
        try {
            servicioNoticia.modificarNoticia(archivo,id, titulo, cuerpo);
            return "redirect:../listar";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "modificarNoticia.html";
        }

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo) {
        servicioNoticia.darDeBajaNoticia(id);
        return "redirect:../listar";
    }
}
