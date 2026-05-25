package com.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // CONFIGURACIÓN DE SEGURIDAD

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // rutas públicas

                .requestMatchers("/login")
                .permitAll()

                // solo ADMIN

                .requestMatchers("/usuarios/**")
                .hasRole("ADMIN")

                // ADMIN o EMPLEADO

                .requestMatchers("/reservas/**")
                .hasAnyRole("ADMIN", "EMPLEADO")

                // cualquier otra autenticada

                .anyRequest()
                .authenticated()
            )

            // activar Basic Auth

            .httpBasic(httpBasic -> {});

        return http.build();
    }

    // USUARIOS DE PRUEBA

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .roles("ADMIN")
                .build();

        UserDetails empleado = User
                .withDefaultPasswordEncoder()
                .username("empleado")
                .password("1234")
                .roles("EMPLEADO")
                .build();

        return new InMemoryUserDetailsManager(
                admin,
                empleado
        );
    }
}