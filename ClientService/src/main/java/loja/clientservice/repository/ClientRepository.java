package loja.clientservice.repository;

import loja.clientservice.entity.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    Optional<ClientModel> findByUsername(String username);

    ClientModel findByEmail(String email);
}
