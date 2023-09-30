package com.example.ProyectoIntegradorMakaia.Config;

import com.example.ProyectoIntegradorMakaia.Security.JwtAuthenticationFilter;
import com.example.ProyectoIntegradorMakaia.Security.JwtAuthorizationFilter;
import com.example.ProyectoIntegradorMakaia.Utils.AuthorityName;
import com.example.ProyectoIntegradorMakaia.Utils.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {

    //private final UserDetailsService userDetailsService;


    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }



    @Bean
    SecurityFilterChain filterChain (HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception{
        JwtAuthenticationFilter jwtAuthenticationFilter= new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

    return httpSecurity
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET).hasAuthority(AuthorityName.READ.name())
            .antMatchers(HttpMethod.POST).hasAuthority(AuthorityName.WRITE.name())
            .antMatchers(HttpMethod.DELETE).hasAuthority(AuthorityName.DELETE.name())
            .antMatchers("/v1/public/**").permitAll()
            .antMatchers("/v1/manage/**").hasRole(RoleName.ROLE_MANAGE.name())
            .antMatchers("/v1/**").hasRole(RoleName.ROLE_ADMIN.name())
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(jwtAuthorizationFilter)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("manage")
                .password(passwordEncoder().encode("manage"))
                .roles(RoleName.ROLE_MANAGE.name(), RoleName.ROLE_USER.name())
                .authorities(AuthorityName.WRITE.name(),AuthorityName.READ.name())
                .build());

        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles(RoleName.ROLE_ADMIN.name(), RoleName.ROLE_MANAGE.name(), RoleName.ROLE_USER.name())
                .authorities(AuthorityName.WRITE.name(),AuthorityName.READ.name(),AuthorityName.DELETE.name())
                .build());

        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles(RoleName.ROLE_USER.name())
                .authorities(AuthorityName.WRITE.name())
                .build());

        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
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








//
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/**").hasAnyRole("ADMIN", "READ", "WRITE")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .logout().permitAll();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
}