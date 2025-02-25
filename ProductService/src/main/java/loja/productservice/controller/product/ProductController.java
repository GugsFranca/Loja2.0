package loja.productservice.controller.product;

import loja.productservice.entity.products.ProductRequest;
import loja.productservice.entity.products.ProductResponse;
import loja.productservice.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResponse> findByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @PostMapping()
    public ResponseEntity<String> addProduct(@RequestBody @Validated ProductRequest request) {
        return ResponseEntity.ok(service.addProduct(request));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProductResponse> sendToCart(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        return ResponseEntity.ok(service.sendToCart(token, id));
    }

}
