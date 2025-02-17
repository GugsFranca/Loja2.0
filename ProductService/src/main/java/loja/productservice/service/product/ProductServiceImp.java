package loja.productservice.service.product;

import jakarta.persistence.EntityNotFoundException;
import loja.productservice.entity.products.ProductRequest;
import loja.productservice.entity.products.ProductResponse;
import loja.productservice.repository.category.CategoryRepository;
import loja.productservice.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
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
        return productList;
    }

    @Override
    public ProductResponse findByName(String name) {
        return repository.findByName(name).map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product does not exist with this name:: " + name));
    }

    @Override
    public String addProduct(ProductRequest request) {
        if (categoryRepository.findByCategoryName(request.category().getCategoryName()).isEmpty()){
            categoryRepository.save(request.category());
        }
        return repository.save(mapper.toProductModel(request)).getName();
    }

    @Override
    public ProductResponse sendToCart(Long id) {
        //TODO
        return null;
    }
}
