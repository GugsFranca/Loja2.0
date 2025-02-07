package loja.gatewayservice.config;

import loja.gatewayservice.service.JwtUtils;
import loja.gatewayservice.service.RoutValidator;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RefreshScope
public class AuthenticationFilter implements GatewayFilter {

    private final RoutValidator validator;
    private final JwtUtils utils;

    public AuthenticationFilter(RoutValidator validator, JwtUtils utils) {
        this.validator = validator;
        this.utils = utils;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (validator.isSecured.test(request)) {
            String token = request.getHeaders().getFirst("Authorization");
            if (token == null || utils.isExpired(token)) {
                return unauthorized(exchange);
            }
        }


        return chain.filter(exchange);
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
