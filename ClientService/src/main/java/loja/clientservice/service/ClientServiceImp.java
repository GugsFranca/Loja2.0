package loja.clientservice.service;

import jakarta.persistence.EntityNotFoundException;
import loja.clientservice.entity.ClientModel;
import loja.clientservice.entity.ClientRequest;
import loja.clientservice.entity.ClientRequestLogin;
import loja.clientservice.entity.ClientResponse;
import loja.clientservice.repository.ClientRepository;
import loja.clientservice.service.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
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
        ClientModel client = repository.findByEmail(login.getEmail());
        if (client != null && checkPassword(login.getPassword(), client.getPassword())){
            log.info(client.toString());
            return client;
        }
        log.error("Client não esta disponivel");
        return null;
    }

    @Override
    public List<ClientResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public HttpStatus delete(ClientRequestLogin login) {

        ClientModel client = repository.findByEmail(login.getEmail());
        if (client != null && checkPassword(login.getPassword(), client.getPassword())){
            repository.delete(client);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    public boolean checkPassword(String rawPassword, String securedPassword){
        return BCrypt.checkpw(rawPassword, securedPassword);
    }
}
