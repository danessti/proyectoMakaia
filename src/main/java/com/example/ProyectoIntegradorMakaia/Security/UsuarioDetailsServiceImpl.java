package com.example.ProyectoIntegradorMakaia.Security;

import com.example.ProyectoIntegradorMakaia.Models.UserClient;
import com.example.ProyectoIntegradorMakaia.Repositories.UserClientRepository;
import com.example.ProyectoIntegradorMakaia.Utils.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UsuarioDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserClientRepository userClientRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserClient> userOptional= userClientRepository
                .findByUsernameOrEmail(username, username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("El usuario con el nombre de usuario/correo electrónico " + username + " no existe");
        }

        UserClient userClient = userOptional.get();

        UserDetails userDetails = User.builder()
                .username(userClient.getUsername())
                .password(userClient.getPassword())
                .roles(RoleName.ROLE_USER.name())
                .build();

        return userDetails;
    }
}