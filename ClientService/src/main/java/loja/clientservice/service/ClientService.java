package loja.clientservice.service;

import loja.clientservice.entity.ClientModel;
import loja.clientservice.entity.ClientRequest;
import loja.clientservice.entity.ClientRequestLogin;
import loja.clientservice.entity.ClientResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ClientService {

    ClientResponse findByUsername(String username);

    List<ClientResponse> findAll();

    ClientModel save(ClientRequest request);

    HttpStatus delete(ClientRequestLogin login);
    ClientModel login(ClientRequestLogin login);
}
