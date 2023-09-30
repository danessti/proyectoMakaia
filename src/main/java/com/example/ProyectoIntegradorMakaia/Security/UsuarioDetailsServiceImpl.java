package com.example.ProyectoIntegradorMakaia.Security;

import com.example.ProyectoIntegradorMakaia.Models.user.User;
import com.example.ProyectoIntegradorMakaia.Models.user.UserRepository;
import com.example.ProyectoIntegradorMakaia.Utils.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UsuarioDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional= userRepository
                .findByUsernameOrEmail(username, username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("El usuario con el nombre de usuario/correo electrónico " + username + " no existe");
        }

        User user = userOptional.get();

        /*UserDetails userDetails = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(RoleName.ROLE_USER.name())
                .build();*/

        return null;
    }
}
