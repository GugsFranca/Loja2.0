package loja.cartservice.service;

import loja.cartservice.entity.CartEntity;
import loja.cartservice.entity.CartEntityResponse;
import loja.cartservice.entity.CartLine;
import loja.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;
    private final JwtUtils jwtUtils;
    private final CartMapper mapper;

    public void addToCart(String token, CartLine cartLine) {
        if (cartLine.getProductModel() == null) {
            throw new NullPointerException("Product is null");
        }
        Long clientId = Long.parseLong(jwtUtils.getClaims(token).getSubject());
        CartEntity cart = repository.findByClientId(clientId);

        if (cart == null) {
            cart = CartEntity.builder()
                    .clientId(clientId)
                    .quantity(0)
                    .totalAmount(0.0)
                    .cartLine(new ArrayList<>()).build();
        }
        cart.getCartLine().add(cartLine);
        cart.setQuantity(cart.getQuantity() + cartLine.getProductModel().quantity());
        cart.setTotalAmount(cart.getTotalAmount() + cartLine.getProductModel().preco().doubleValue());

        repository.save(cart);
    }

    public List<CartEntityResponse> findAll() {
        var cart = repository.findAll().stream().map(mapper::toEntityResponse).collect(Collectors.toList());
        if (cart.isEmpty()) {
            throw new IllegalStateException("Nothing to see here");
        }
        return cart;
    }
}
