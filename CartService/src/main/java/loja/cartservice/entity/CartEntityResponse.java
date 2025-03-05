package loja.cartservice.entity;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link CartEntity}
 */
public record CartEntityResponse(
        Long clientId,
        List<CartLine> cartLine,
        int quantity,
        Double totalAmount) implements Serializable {
}