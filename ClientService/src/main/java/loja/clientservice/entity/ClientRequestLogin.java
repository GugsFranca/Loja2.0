package loja.clientservice.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link ClientModel}
 */
@Data
public class ClientRequestLogin implements Serializable {

        @Email(message = "Email not found")
        @NotBlank(message = "Email is required")
        private String email;
        @NotBlank(message = "Password is required")
        private String password;
}