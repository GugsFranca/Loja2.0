package loja.cartservice.controller;

import loja.cartservice.entity.CartEntityResponse;
import loja.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService service;

    @GetMapping
    public ResponseEntity<List<CartEntityResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
