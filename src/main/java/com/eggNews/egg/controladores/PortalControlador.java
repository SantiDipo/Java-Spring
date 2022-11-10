/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Santiago D'Ippolito
 */
@Controller 
@RequestMapping("/")
public class PortalControlador {
    
    @GetMapping("/")
    public String index(){
        return "Indexlogin.html";
    }
    
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
    
    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";
}
}
