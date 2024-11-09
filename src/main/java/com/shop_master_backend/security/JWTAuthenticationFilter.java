package com.shop_master_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Método que intercepta cada solicitud HTTP y verifica si hay un token JWT presente en el encabezado.
     * Si el token es válido, autentica al usuario
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Se obtiene el encabezado 'Authorization' de la solicitud
        final String authHeader = request.getHeader("Authorization");

        // Si el encabezado no está presente o no contiene un token Bearer, se continúa con el siguiente filtro
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Se extrae el token JWT del encabezado
            final String jwt = authHeader.substring(7);
            // Se obtiene el nombre de usuario del token
            final String username = jwtService.extractUsername(jwt);

            // Se verifica si el usuario no está autenticado en el contexto de seguridad
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (username != null && authentication == null) {
                // Carga los detalles del usuario desde la base de datos
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Si el token es válido, se crea el objeto de autenticación
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    // Se crea un token de autenticación basado en el usuario y sus roles
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    // Se establecen los detalles de la solicitud en el token de autenticación
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Se coloca el token de autenticación en el contexto de seguridad de Spring
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }

}
