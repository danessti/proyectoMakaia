package com.example.ProyectoIntegradorMakaia.Security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String username;
    private String email;
    private String password;

}
