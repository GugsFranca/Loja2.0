package loja.cartservice.repository;

import loja.cartservice.entity.CartEntity;
import loja.cartservice.entity.CartLine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CartLineRepository extends MongoRepository<CartLine, String> {
}
