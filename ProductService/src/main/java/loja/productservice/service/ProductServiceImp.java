package loja.productservice.service;

import jakarta.persistence.EntityNotFoundException;
import loja.productservice.entity.ProductRequest;
import loja.productservice.entity.ProductResponse;
import loja.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public ProductResponse findById(Long id) {
        return repository.findById(id).map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product does not exist with this id:: " + id));
    }

    @Override
    public List<ProductResponse> findAll() {
        var productList = repository.findAll().stream().map(mapper::toResponse).toList();
        if (productList.isEmpty()) {
            throw new EntityNotFoundException("There is no products in this database");
        }
        return null;
    }

    @Override
    public ProductResponse findByName(String name) {
        return repository.findByName(name).map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product does not exist with this name:: " + name));
    }

    @Override
    public String addProduct(ProductRequest request) {
        return repository.save(mapper.toProductModel(request)).getName();
    }

    @Override
    public ProductResponse sendToCart(Long id) {
        //TODO
        return null;
    }
}
