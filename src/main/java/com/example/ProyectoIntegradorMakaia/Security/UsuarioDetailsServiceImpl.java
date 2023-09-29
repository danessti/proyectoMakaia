package com.example.ProyectoIntegradorMakaia.Security;

import com.example.ProyectoIntegradorMakaia.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Optional;

public class UsuarioDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.example.ProyectoIntegradorMakaia.Models.User> userOptional= userRepository
                .findByUsernameOrEmail(username, username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("El usuario con el nombre de usuario/correo electrónico " + username + " no existe");
        }

        com.example.ProyectoIntegradorMakaia.Models.User user = userOptional.get();

        return null;
//Arreglar luego
//                new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                (Collection<? extends GrantedAuthority>) user.getAuthorities());

    }
}
