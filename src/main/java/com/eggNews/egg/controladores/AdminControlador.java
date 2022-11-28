/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.controladores;

import com.eggNews.egg.entidades.Usuario;
import com.eggNews.egg.servicios.PeriodistaServicio;
import com.eggNews.egg.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Santiago D'Ippolito
 */
@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private UsuarioServicio usuarioservicio;

    @Autowired
    private PeriodistaServicio periodisraServicio;
    
    @GetMapping("/dashboard")
    public String panelAdmin() {
        return "panel.html";

    }
    
    @GetMapping("/usuarios")
    public String listar(ModelMap modelo){
        List<Usuario> usuarios = usuarioservicio.listarUsuario();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_list.html";
    }
    
    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id){
        usuarioservicio.cambiarRol(id);
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/modificarActivo/{id}")
    public String cambiarActivo(@PathVariable String id){
        usuarioservicio.cambiarActivo(id);
        return "redirect:/admin/usuarios";
    }
    
    
}
