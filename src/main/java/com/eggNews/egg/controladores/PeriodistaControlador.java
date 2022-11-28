/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.controladores;


import com.eggNews.egg.servicios.PeriodistaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Santiago D'Ippolito
 */
@Controller
@RequestMapping("/periodista")
public class PeriodistaControlador {

    @Autowired
    private PeriodistaServicio servicioperiodista;
    
    
    @GetMapping("/dashboard")
    public String panelAdmin() {
        return "panel.html";

    }
    @GetMapping("/ingresar")
    public String ingresarSalario() {
        return "ingresarSueldo.html";
    }

    @PatchMapping  ("/sueldo/{id}")
    public String sueldoMensual(String id, Integer sueldoMensual) {
        servicioperiodista.ingresarSueldo(id, sueldoMensual);
        return "redirect:/admin/dashboard";
    }

}
