package com.shop_master_backend.config;


import com.shop_master_backend.security.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Define la cadena de filtros de seguridad que se aplicarán a todas las solicitudes HTTP.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Desactiva la protección CSRF, ya que se gestionará mediante tokens JWT
                .csrf(csrf -> csrf.disable())
                // Habilitar la configuración del CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Configuración de las reglas de autorización de las solicitudes HTTP.
                .authorizeHttpRequests(authRequest -> authRequest
                        // Permitir el acceso a recursos estáticos.
                        .requestMatchers("/styles/**", "/images/**").permitAll()
                        // Permitir todas las solicitudes que comiencen con /auth, como login o registro.
                        .requestMatchers("/auth/**").permitAll()
                        // Cualquier otra solicitud debe estar autenticada.
                        .anyRequest().authenticated()
                )
                // Define la política de manejo de sesiones como STATELESS, es decir, sin almacenamiento de estado en el servidor
                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura el proveedor de autenticación que válida las credenciales
                .authenticationProvider(authenticationProvider)
                // Añade el filtro de autenticación JWT antes del filtro de autenticación de usuario y contraseña
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Configuración de CORS para permitir solicitudes desde dominios específicos.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Define el dominio permitido
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));

        // Define los métodos HTTP permitidos en las solicitudes CORS
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Define los encabezados permitidos en las solicitudes CORS, como "Authorization" y "Content-Type"
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        // Asigna las configuraciones de CORS a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
