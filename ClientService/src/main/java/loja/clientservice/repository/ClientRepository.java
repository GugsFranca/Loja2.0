package loja.clientservice.repository;

import loja.clientservice.entity.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {

}
