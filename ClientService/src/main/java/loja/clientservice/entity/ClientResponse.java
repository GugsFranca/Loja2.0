package loja.clientservice.entity;

import java.io.Serializable;

/**
 * DTO for {@link ClientModel}
 */
public record ClientResponse(
        String username,
        String email,
        RoleName role) implements Serializable {
}