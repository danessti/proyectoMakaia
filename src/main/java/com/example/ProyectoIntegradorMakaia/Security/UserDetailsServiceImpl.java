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


public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserClientRepository userClientRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<UserClient> userOptional= userClientRepository
                .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("El usuario con el nombre de usuario/correo electrónico " + usernameOrEmail + " no existe");
        }

        UserClient userClient = userOptional.get();

        return User.builder()
                .username(userClient.getUsername())
                .password(userClient.getPassword())
                .roles(userClient.getRoleName().name())
                .build();
    }
}