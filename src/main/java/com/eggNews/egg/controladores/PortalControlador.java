/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.controladores;

import com.eggNews.egg.enumeraciones.Rol;
import com.eggNews.egg.exepciones.MiException;
import com.eggNews.egg.servicios.UsuarioServicio;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
public class PortalControlador {
    
    @Autowired
    UsuarioServicio usuarioServicio; 
    
    @GetMapping("/")
    public String index(){
        return "Indexlogin.html";
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        if (error != null) {
            modelo.put("error", "Usuario o contraseña invalidos!");
        }
        return "login.html";
    }
    
    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";
}
    @PostMapping("/registro")
    public String registro(@RequestParam String nombreUsuario,@RequestParam  String password,@RequestParam  String password2, ModelMap modelo,String alta) throws MiException{
        try {
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaFinal = fecha.parse(alta);
            usuarioServicio.registrar(nombreUsuario, password, password2, fechaFinal, Rol.USUARIO, true);
            modelo.put("Exito!", "Usuario registrado correcatamente");
            return "Indexlogin.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            modelo.put("Error!", e.getMessage());
            return "registro.html";
        }
       
    }
}
