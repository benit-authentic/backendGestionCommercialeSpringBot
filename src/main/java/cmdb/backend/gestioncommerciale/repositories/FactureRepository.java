package cmdb.backend.gestioncommerciale.repositories;

import cmdb.backend.gestioncommerciale.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByEtat(String etat);           // Rechercher par état
    Optional<Facture> findByCommandeId(Long commandeId);       // Trouver une facture liée à une commande
    Facture findByReferenceComptable(String referenceComptable);
}
