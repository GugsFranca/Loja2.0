package loja.clientservice.service;

import loja.clientservice.entity.ClientModel;
import loja.clientservice.entity.ClientRequest;
import loja.clientservice.entity.ClientResponse;

import java.util.List;

public interface ClientService {

    ClientResponse findByUsername(String username);
    List<ClientModel> findAll();
    ClientModel save(ClientRequest request);
    void delete(String email, String password);

}
