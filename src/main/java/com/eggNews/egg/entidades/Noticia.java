/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.entidades;



import javafx.scene.image.Image;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




/**
 *
 * @author Santiago D'Ippolito
 */
@Entity
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String cuerpo;
//    private Image foto; 

    public Noticia() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

//    public void setFoto(Image foto) {
//        this.foto = foto;
//    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

//    public Image getFoto() {
//        return foto;
//    }
//    
    
}
