package loja.clientservice.service;

import loja.clientservice.entity.ClientModel;
import loja.clientservice.entity.ClientRequest;
import loja.clientservice.entity.ClientResponse;
import loja.clientservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {
    private final ClientRepository repository;
    @Override
    public ClientResponse findByUsername(String username) {
        return null;
    }

    @Override
    public List<ClientModel> findAll() {
        return null;
    }

    @Override
    public ClientModel save(ClientRequest request) {
        return null;
    }

    @Override
    public void delete(String email, String password) {

    }
}
