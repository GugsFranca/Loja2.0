package loja.authservice.service;

import loja.authservice.entity.AuthRequest;
import loja.authservice.entity.AuthRequestLogin;
import loja.authservice.entity.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRequest request);

    AuthResponse login(AuthRequestLogin login);
}
