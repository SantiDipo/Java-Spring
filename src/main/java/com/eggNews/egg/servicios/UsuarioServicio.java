/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eggNews.egg.servicios;

import com.eggNews.egg.entidades.Imagen;
import com.eggNews.egg.entidades.Usuario;
import com.eggNews.egg.enumeraciones.Rol;
import com.eggNews.egg.exepciones.MiException;
import com.eggNews.egg.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Santiago D'Ippolito
 */
@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ImagenServicio imagenservicio;

    @Transactional
    public void registrar(MultipartFile archivo, String nombreUsuario, String password, String password2, Date alta, Rol rol, boolean activo) throws MiException {

        validar(nombreUsuario, password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setPassword(new BCryptPasswordEncoder().encode(password2));
        usuario.setAlta(alta);
        usuario.setRol(rol.USUARIO);
        Imagen imagen = imagenservicio.guardarImagen(archivo);
        usuario.setImagen(imagen);
        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void actualizar(String id, MultipartFile archivo, String nombreUsuario, String password, String password2, Rol rol, boolean activo) throws MiException {

        validar(nombreUsuario, password, password2);
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Usuario usuario = new Usuario();

            usuario.setNombreUsuario(nombreUsuario);
            usuario.setPassword(new BCryptPasswordEncoder().encode(password));
            usuario.setRol(rol.USUARIO);
            String idImagen = null;
            if (usuario.getImagen() != null) {
                idImagen = usuario.getImagen().getId();
            }
            Imagen imagen = imagenservicio.actualizarImagen(archivo, idImagen);
            usuario.setImagen(imagen);
            usuarioRepositorio.save(usuario);
        }

    }
    
       public Usuario getOne(String id) {
        return usuarioRepositorio.getOne(id);
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

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getNombreUsuario(), usuario.getPassword(), permisos);
        } else {
            return null;
        }
    }
}
