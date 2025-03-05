package loja.productservice.service.product;

import jakarta.persistence.EntityNotFoundException;
import loja.productservice.Kafka.KafkaMessage;
import loja.productservice.Kafka.ProductProducer;
import loja.productservice.entity.category.CategoryModel;
import loja.productservice.entity.products.ProductModel;
import loja.productservice.entity.products.ProductRequest;
import loja.productservice.entity.products.ProductResponse;
import loja.productservice.repository.category.CategoryRepository;
import loja.productservice.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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
    public List<ProductModel> findAll() {
        var productList = repository.findAll();
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
    public ProductResponse sendToCart(String token, Long id, int quantity) {
        ProductModel product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        if (product.getQuantity() < 1 ){
            throw new IllegalStateException("Product out of stock: " + product.getId());
        }
        if (quantity <= 0){
            throw new IllegalStateException("Not enough stock for product: " + product.getId());
        }
        if (product.getQuantity()<quantity){
            throw new IllegalStateException("Not enough stock for product: " + product.getId());
        }

        ProductModel productForCart = mapper.toSendForCart(product, quantity);

        KafkaMessage kafkaMessage = KafkaMessage.builder().token(token).model(productForCart).build();
        product.setQuantity(product.getQuantity() - quantity);
        try {
            producer.sendProduct(kafkaMessage);
            log.info("Product sent to cart successfully: {}", productForCart.getId());

            return mapper.toResponse(productForCart);
        } catch (Exception e) {
            log.error("Failed to send product to cart: {}", e.getMessage());
            throw new KafkaException("Failed to send product to cart", e);
        }
    }
}
