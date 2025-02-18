package loja.productservice.service.product;

import jakarta.persistence.EntityNotFoundException;
import loja.productservice.Kafka.ProductProducer;
import loja.productservice.entity.category.CategoryModel;
import loja.productservice.entity.products.ProductModel;
import loja.productservice.entity.products.ProductRequest;
import loja.productservice.entity.products.ProductResponse;
import loja.productservice.repository.category.CategoryRepository;
import loja.productservice.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;
    private final ProductProducer producer;

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
        CategoryModel category = categoryRepository.findByCategoryName(request.category().getCategoryName())
                .orElseGet(() -> categoryRepository.save(request.category()));
        ProductModel product = mapper.toProductModel(request);
        product.setCategory(category);

        return repository.save(product).getName();
    }

    @Override
    public ProductResponse sendToCart(Long id) {
        Optional<ProductModel> product = repository.findById(id);
        if (product.isPresent()) {
            producer.sendProduct(product.get());
            return mapper.toResponse(product.get());
        }
        return null;
    }
}
