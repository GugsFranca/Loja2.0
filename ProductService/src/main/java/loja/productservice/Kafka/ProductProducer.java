package loja.productservice.Kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductProducer {
    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    public void sendProduct(KafkaMessage kafkaMessage){
        Message<KafkaMessage> message = MessageBuilder
                .withPayload(kafkaMessage)
                .setHeader(KafkaHeaders.TOPIC, "product-topic")
                .build();
        kafkaTemplate.send(message);
        log.info("Sending product");
    }
}
