package loja.productservice.controller.product;

import loja.productservice.entity.products.ProductRequest;
import loja.productservice.entity.products.ProductResponse;
import loja.productservice.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    public ResponseEntity<ProductResponse>  findById(Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    public ResponseEntity<List<ProductResponse>>  findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    public ResponseEntity<ProductResponse>  findByName(String name){
        return ResponseEntity.ok(service.findByName(name));
    }
    public ResponseEntity<String>  addProduct(ProductRequest request){
        return ResponseEntity.ok(service.addProduct(request));
    }
    public ResponseEntity<ProductResponse>  sendToCart(Long id){
        //TODO
        return null;
    }

}
