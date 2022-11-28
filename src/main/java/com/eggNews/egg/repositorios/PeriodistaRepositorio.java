/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.repositorios;

import com.eggNews.egg.entidades.Periodista;
import com.eggNews.egg.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Santiago D'Ippolito
 */
@Repository
public interface PeriodistaRepositorio extends UsuarioRepositorio<Usuario> {

}

