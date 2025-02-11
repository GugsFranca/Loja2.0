package loja.clientservice.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link ClientModel}
 */
public record ClientRequest(
        @Size(message = "Username should have 8 to 16 characters", min = 8, max = 16)
        @NotBlank(message = "Username is required")
        String username,
        @Email(message = "Email not found")
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        String password,
        @NotNull RoleName role) implements Serializable {
}