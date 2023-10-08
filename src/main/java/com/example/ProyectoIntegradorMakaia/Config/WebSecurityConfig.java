package com.example.ProyectoIntegradorMakaia.Config;

import com.example.ProyectoIntegradorMakaia.Security.jwt.JwtAuthenticationFilter;
import com.example.ProyectoIntegradorMakaia.Security.jwt.JwtAuthorizationFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableConfigurationProperties
public class WebSecurityConfig {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    public WebSecurityConfig(JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }


    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity,AuthenticationManager authManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter =new JwtAuthenticationFilter();

        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/v1/airlines").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/**").authenticated()
                .antMatchers("/v1/public").hasRole("USER")
                .antMatchers("/v1/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET).hasAuthority("READ")
                .antMatchers(HttpMethod.POST).hasAuthority("WRITE")
                .antMatchers(HttpMethod.DELETE).hasAuthority("DELETE")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("adminn")
                .password(passwordEncoder().encode("1234"))
                .roles()
                .build()
        );

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

}