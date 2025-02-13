package loja.clientservice.controller;

import loja.clientservice.entity.ClientModel;
import loja.clientservice.entity.ClientRequest;
import loja.clientservice.entity.ClientRequestLogin;
import loja.clientservice.entity.ClientResponse;
import loja.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;

    @GetMapping("/{username}")
    public ResponseEntity<ClientResponse> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(service.findByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<ClientModel> save(@RequestBody ClientRequest request) {
        var client = service.save(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(request.username())
                .toUri();
        return ResponseEntity.created(location).body(client);
    }
    @PostMapping("/login")
    public ResponseEntity<ClientModel> login(@RequestBody ClientRequestLogin login) {
        return ResponseEntity.ok(service.login(login));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody ClientRequestLogin login) {
        return ResponseEntity.status(service.delete(login)).build();
    }
}
