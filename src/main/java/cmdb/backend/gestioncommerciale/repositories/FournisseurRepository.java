package cmdb.backend.gestioncommerciale.repositories;

import cmdb.backend.gestioncommerciale.entities.Fournisseur;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends PersonneRepository<Fournisseur> {
    // Méthodes spécifiques aux fournisseurs
}