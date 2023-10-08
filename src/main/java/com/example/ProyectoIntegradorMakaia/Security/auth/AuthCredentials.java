package com.example.ProyectoIntegradorMakaia.Security.auth;

import com.example.ProyectoIntegradorMakaia.Models.Authority;
import com.example.ProyectoIntegradorMakaia.Models.Roles;
import com.example.ProyectoIntegradorMakaia.Utils.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthCredentials {
    private String username;
    private String email;
    private String password;
    private Roles roles;
    private List<Authority> authorities;

}