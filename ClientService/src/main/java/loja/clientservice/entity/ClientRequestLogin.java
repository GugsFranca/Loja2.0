package loja.clientservice.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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