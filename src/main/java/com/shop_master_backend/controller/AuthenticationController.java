package com.shop_master_backend.controller;

import com.shop_master_backend.dto.auth.AuthResponseDTO;
import com.shop_master_backend.dto.auth.LoginDTO;
import com.shop_master_backend.dto.auth.RegisterDTO;
import com.shop_master_backend.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterDTO registerDTO) {
        AuthResponseDTO authResponse = authenticationService.register(registerDTO);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponse = authenticationService.login(loginDTO);
        return ResponseEntity.ok(authResponse);
    }

}
