package com.shop_master_backend.service.interfaces;

import com.shop_master_backend.dto.auth.AuthResponseDTO;
import com.shop_master_backend.dto.auth.LoginDTO;
import com.shop_master_backend.dto.auth.RegisterDTO;

public interface AuthenticationService {

    AuthResponseDTO login(LoginDTO request);

    AuthResponseDTO register(RegisterDTO request);

}
