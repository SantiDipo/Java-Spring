/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.repositorios;

import com.eggNews.egg.entidades.Periodista;
import com.eggNews.egg.entidades.Usuario;
import com.eggNews.egg.enumeraciones.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Santiago D'Ippolito
 */
@NoRepositoryBean
public interface UsuarioRepositorio <T extends Usuario> extends JpaRepository<T, String>{
    
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    public T buscarPorNombre(@Param("nombreUsuario")String nombreUsuario);
    
}
