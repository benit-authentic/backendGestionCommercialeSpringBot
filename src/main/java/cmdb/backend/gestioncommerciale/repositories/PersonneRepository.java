package cmdb.backend.gestioncommerciale.repositories;

import cmdb.backend.gestioncommerciale.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonneRepository<T extends Personne> extends JpaRepository<T, Long> {
    // Rechercher par email
    T findByEmail(String email);

    // Rechercher par numéro de compte
    T findByNumCompte(String numCompte);

    // Rechercher par nom
    List<T> findByNom(String nom);
}
