package loja.clientservice.service.mapper;

import loja.clientservice.entity.ClientModel;
import loja.clientservice.entity.ClientRequest;
import loja.clientservice.entity.ClientResponse;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {
    public ClientResponse toResponse(ClientModel clientModel) {
        return new ClientResponse(clientModel.getUsername(), clientModel.getEmail(), clientModel.getRole());
    }

    public ClientModel toClientModel(ClientRequest request) {
        return ClientModel.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .role(request.role())
                .build();
    }
}
