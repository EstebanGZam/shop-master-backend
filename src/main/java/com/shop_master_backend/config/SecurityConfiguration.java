package com.shop_master_backend.config;


import com.shop_master_backend.security.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
                .csrf(AbstractHttpConfigurer::disable)
                // Deshabilitar el CORS, ya que se configurará manualmente con un filtro separado
                .cors(cors -> cors.disable())
                // Configuración de las reglas de autorización de las solicitudes HTTP.
                .authorizeHttpRequests(authRequest -> authRequest
                        // Permitir el acceso a recursos estáticos.
                        .requestMatchers("/styles/**", "/images/**").permitAll()
                        // Permitir todas las solicitudes que comiencen con /auth, como login o registro.
                        .requestMatchers("/auth/**").permitAll()
                        // Permitir conexiones SSE
                        .requestMatchers("/products/stream").permitAll()
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
     * Configura un filtro CORS global para manejar las solicitudes desde cualquier origen.
     */
    @Bean
    public FilterRegistrationBean<?> simpleCorsFilter() {
        // Configuración de la fuente CORS basada en URL
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permitir el envío de cookies y credenciales
        config.setAllowCredentials(true);

        // Permitir todos los dominios
        config.setAllowedOriginPatterns(Collections.singletonList("*"));

        // Permitir todos los métodos HTTP
        config.setAllowedMethods(Collections.singletonList("*"));

        // Permitir todos los encabezados
        config.setAllowedHeaders(Collections.singletonList("*"));

        // Registrar la configuración para todas las rutas (/**)
        source.registerCorsConfiguration("/**", config);

        // Registrar el filtro con la configuración definida y establecer alta prioridad
        FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }

}
