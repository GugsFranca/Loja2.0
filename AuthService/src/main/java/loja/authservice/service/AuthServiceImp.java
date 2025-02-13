package loja.authservice.service;

import loja.authservice.entity.AuthRequest;
import loja.authservice.entity.AuthRequestLogin;
import loja.authservice.entity.AuthResponse;
import loja.authservice.entity.ClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final RestTemplate restTemplate;
    private final JwtUtils jwtUtils;

    public AuthResponse register(AuthRequest request){

        ClientModel client = restTemplate.postForObject("http://localhost:9000/client/save", request, ClientModel.class);

        String accessToken = jwtUtils.generate(client.id().toString(), client.role(), "ACCESS");
        String refreshToken = jwtUtils.generate(client.id().toString(), client.role(),"REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public AuthResponse login(AuthRequestLogin login) {

        var client = restTemplate.postForObject("http://localhost:9000/client/login", login , ClientModel.class);
            String accessToken = jwtUtils.generate(client.id().toString(), client.role(), "ACCESS");
            String refreshToken = jwtUtils.generate(client.id().toString(), client.role(),"REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}
