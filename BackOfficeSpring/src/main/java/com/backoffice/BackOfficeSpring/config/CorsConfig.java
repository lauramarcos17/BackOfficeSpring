package com.backoffice.BackOfficeSpring.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.backoffice.BackOfficeSpring.login.JwtAuthFilter;


@Configuration
public class CorsConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/login").permitAll()
                
            .requestMatchers("/api/clientes").hasAnyRole("Administrador","Supervisor","Backoffice")
            .requestMatchers("/api/generarCopiaSeguridad").hasAnyRole("Administrador","Backoffice")
            .requestMatchers("/api/backups").hasAnyRole("Administrador","Backoffice")
            .requestMatchers("/api/eliminarCopia").hasAnyRole("Administrador","Backoffice")
            .requestMatchers("/api/restaurarCopia").hasAnyRole("Administrador","Backoffice")
            .requestMatchers("/api/generarMigracion").hasAnyRole("Administrador","Backoffice")
            .requestMatchers("/api/migraciones").hasAnyRole("Administrador")
            .requestMatchers("/api/eliminarMigracion").hasRole("Administrador")
            .requestMatchers("/api/restaurarMigracion").hasAnyRole("Administrador")
            .requestMatchers("/api/generarLog").hasAnyRole("Administrador", "Supervisor","Backoffice")
            .requestMatchers("/api/logs").hasAnyRole("Administrador","Supervisor","Backoffice")
            .anyRequest().authenticated()
            )
            .addFilterBefore(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class); //imp
            

        return http.build();
    }
   
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowedOrigins(List.of(
            "http://localhost:4200",
            "http://backoffice.practicas", // Ojo: sin ruta /awj-back/backoffice/api
            "http://backoffice.practicas:4200")); // Orígenes permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "FETCH"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // Si estás usando cookies o headers de autenticación

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplica a todos los endpoints
        return source;
    }
}