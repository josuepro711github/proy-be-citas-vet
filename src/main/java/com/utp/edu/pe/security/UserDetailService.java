package com.utp.edu.pe.security;

import com.utp.edu.pe.model.Usuario;
import com.utp.edu.pe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UsuarioRepository repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repositorio.findByEmail(username);

        if(usuario!=null) {

            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getContrasenia())
                    .roles(usuario.getRol().getTipo_rol())
                    .build();
        }else {
            throw new UsernameNotFoundException("Usuario no encontrado: "+username);
        }
    }
}
