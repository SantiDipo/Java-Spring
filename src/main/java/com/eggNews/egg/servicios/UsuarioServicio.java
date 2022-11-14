/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.servicios;

import com.eggNews.egg.entidades.Usuario;
import com.eggNews.egg.enumeraciones.Rol;
import com.eggNews.egg.exepciones.MiException;
import com.eggNews.egg.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santiago D'Ippolito
 */
@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrar(String nombreUsuario, String password, String password2, Date alta, Rol rol, boolean activo) throws MiException {

        validar(nombreUsuario, password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setPassword(new BCryptPasswordEncoder().encode(password2));
        usuario.setAlta(alta);
        usuario.setRol(rol.USUARIO);
        usuarioRepositorio.save(usuario);
    }

    public void validar(String nombreUsuario, String password, String password2) throws MiException {
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            throw new MiException("El nombre no debe ser nulo");
        }
        if (password == null || password.isEmpty() || password.length() < 6) {
            throw new MiException("La contraseña no debe ser nula o debe ser mayor a 6 digitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("La constraseñas deben ser iguales");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorNombre(nombreUsuario);

        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);

            return new User(usuario.getNombreUsuario(), usuario.getPassword(), permisos); 
        }
        else{
            return null;
        }
    }
}
