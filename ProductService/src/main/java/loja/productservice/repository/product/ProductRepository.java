package loja.productservice.repository.product;

import loja.productservice.entity.products.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findByName(String name);
}
