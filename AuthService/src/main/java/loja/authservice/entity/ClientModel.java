package loja.authservice.entity;

import lombok.Data;

public record ClientModel(Long id, String username, String password, String email, String role){
}
