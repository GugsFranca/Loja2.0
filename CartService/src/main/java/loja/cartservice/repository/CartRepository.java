package loja.cartservice.repository;

import loja.cartservice.entity.CartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<CartEntity, String> {
    CartEntity findByClientId(Long id);
}
