package loja.gatewayservice.service;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RoutValidator {
    private static final List<String> openEndpoints = List.of("/auth/register", "/client/save");

    public Predicate<ServerHttpRequest> isSecured = request -> openEndpoints.stream().noneMatch(uri -> request.getURI().getPath().equals(uri));
}
