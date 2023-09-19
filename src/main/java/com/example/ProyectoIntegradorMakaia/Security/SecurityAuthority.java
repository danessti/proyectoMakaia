//package com.example.ProyectoIntegradorMakaia.Security;
//
//import com.example.ProyectoIntegradorMakaia.Models.Authority;
//import org.springframework.security.core.GrantedAuthority;
//
//public class SecurityAuthority implements GrantedAuthority {
//
//    private final Authority authority;
//
//    public SecurityAuthority(Authority authority) {
//        this.authority = authority;
//    }
//
//    @Override
//    public String getAuthority() {
//        return authority.getName().toString();
//    }
//
//}