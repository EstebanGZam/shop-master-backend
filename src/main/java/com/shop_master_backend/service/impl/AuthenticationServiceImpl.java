package com.shop_master_backend.service.impl;

import com.shop_master_backend.dto.auth.AuthResponseDTO;
import com.shop_master_backend.dto.auth.LoginDTO;
import com.shop_master_backend.dto.auth.RegisterDTO;
import com.shop_master_backend.entity.sql.Role;
import com.shop_master_backend.entity.sql.User;
import com.shop_master_backend.exception.runtime.RoleNotFoundException;
import com.shop_master_backend.exception.runtime.UserNotFoundException;
import com.shop_master_backend.repository.RoleRepository;
import com.shop_master_backend.repository.UserRepository;
import com.shop_master_backend.security.JWTService;
import com.shop_master_backend.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public AuthResponseDTO register(RegisterDTO request) {
        // Verificar si el usuario ya existe y lanzar excepción si es necesario
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new BadCredentialsException("Username is already taken");
        }

        // Obtener rol por ID y lanzar excepción si no existe
        Role role = roleRepository.findById(request.getRole())
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        // Crear y guardar el usuario
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .roles(Collections.singletonList(role))
                .build();
        userRepository.save(user);

        System.out.println(passwordEncoder.encode(request.getPassword()));

        // Generar token JWT
        String jwtToken = jwtService.generateToken(user);

        return AuthResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponseDTO login(LoginDTO request) {
        try {
            // Autenticar usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // Buscar usuario autenticado
        User authenticatedUser = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Generar token JWT
        String jwtToken = jwtService.generateToken(authenticatedUser);

        return AuthResponseDTO.builder()
                .token(jwtToken)
                .build();
    }
}
