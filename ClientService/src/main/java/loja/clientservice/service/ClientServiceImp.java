package loja.clientservice.service;

import jakarta.persistence.EntityNotFoundException;
import loja.clientservice.entity.ClientModel;
import loja.clientservice.entity.ClientRequest;
import loja.clientservice.entity.ClientRequestLogin;
import loja.clientservice.entity.ClientResponse;
import loja.clientservice.repository.ClientRepository;
import loja.clientservice.service.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientServiceImp implements ClientService {
    private final ClientRepository repository;
    private final ClientMapper mapper;

    @Override
    public ClientResponse findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with name " + username));
    }
    @Override
    public ClientModel save(ClientRequest request) {
        return repository.save(mapper.toClientModel(request));
    }

    public ClientModel login(ClientRequestLogin login) {
        login.setPassword(BCrypt.hashpw(login.getPassword(), BCrypt.gensalt()));
        return repository.findByEmailAndPassword(login.getEmail(), login.getPassword());
    }

    @Override
    public List<ClientResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(ClientRequestLogin login) {
        ClientModel client = repository.findByEmailAndPassword(login.getEmail(), login.getPassword());
        repository.delete(client);
    }
}
