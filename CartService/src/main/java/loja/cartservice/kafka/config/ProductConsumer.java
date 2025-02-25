package loja.cartservice.kafka.config;

import loja.cartservice.entity.CartLine;
import loja.cartservice.kafka.entity.KafkaMessage;
import loja.cartservice.repository.CartLineRepository;
import loja.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductConsumer {
    private final CartLineRepository repository;
    private final CartService service;

    @KafkaListener(topics ="product-topic")
    public void consumeProduct(KafkaMessage message){
        log.info("Consuming Product Topic" + message);
        if (message.getModel() == null){
            throw new NullPointerException("KafkaMessage Product is null");
        }

        CartLine cartLine = new CartLine(message.getModel());
        repository.save(cartLine);
        log.info("cart doido " + cartLine);
        service.addToCart(message.getToken(),cartLine);

    }


}
