/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.controladores;

import com.eggNews.egg.entidades.Periodista;
import com.eggNews.egg.servicios.PeriodistaServicio;
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
@RequestMapping("/periodista")
//@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
public class PeriodistaControlador {

    @Autowired
    private PeriodistaServicio servicioperiodista;

    @GetMapping("/dashboard")
    public String panelAdmin() {
        return "panel.html";
    }

    @GetMapping("/ingresar")
    public String ingresarSalario(ModelMap modelo) {
        Periodista periodista = new Periodista();
        periodista.setId("id prueba");
        modelo.addAttribute("periodista",periodista);
        modelo.addAttribute("prueba", "valor de pureba");
        return "ingresarSueldo.html";
    }

    @PostMapping("/sueldo/{id}")
    public String sueldoMensual(@RequestParam String id,@RequestParam Integer sueldoMensual) {
        servicioperiodista.ingresarSueldo(id, sueldoMensual);
        return "redirect:/admin/dashboard";
    }

}
