package cmdb.backend.gestioncommerciale.repositories;

import cmdb.backend.gestioncommerciale.entities.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PersonneRepository<Client> {
    // Méthodes spécifiques aux clients
}