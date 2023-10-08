package com.example.ProyectoIntegradorMakaia.Security.jwt;

import com.example.ProyectoIntegradorMakaia.Security.auth.AuthCredentials;
import com.example.ProyectoIntegradorMakaia.Security.UserDetailsImpl;
import com.example.ProyectoIntegradorMakaia.Utils.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();

        try{
            authCredentials= new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e){

        }
        UsernamePasswordAuthenticationToken usernamePAT= new UsernamePasswordAuthenticationToken(
                authCredentials.getUsername(),
                authCredentials.getPassword(),
                Collections.emptyList()

        );

        return getAuthenticationManager().authenticate(usernamePAT);
    }

    protected void succesfulAuthentication(HttpServletRequest request,
                                           HttpServletResponse response,
                                           FilterChain chain,
                                           Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDetails= (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils. createToken(userDetails.getUsername(),userDetails.getUsername());

        response.addHeader("Authorization", "Bearer" + token);

        response.getWriter().flush();

        super.successfulAuthentication(request,response,chain, authResult);

    }

}